package com.poliymorf.dagaitem.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliymorf.dagaitem.config.JwtService;
import com.poliymorf.dagaitem.data.dto.request.ChangePasswordRequest;
import com.poliymorf.dagaitem.data.entity.Biodata;
import com.poliymorf.dagaitem.data.entity.UserCheckSum;
import com.poliymorf.dagaitem.data.enums.EnumSum;
import com.poliymorf.dagaitem.data.enums.Operation;
import com.poliymorf.dagaitem.data.enums.RoleEnum;
import com.poliymorf.dagaitem.repository.*;
import com.poliymorf.dagaitem.service.CheckSumService;
import com.poliymorf.dagaitem.util.EncryptUtil;
import com.poliymorf.dagaitem.util.RandomUtil;
import com.poliymorf.dagaitem.util.TimePars;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.data.dto.request.AuthenticationRequest;
import com.poliymorf.dagaitem.data.dto.request.RegisterRequest;
import com.poliymorf.dagaitem.data.dto.response.AuthenticationResponse;
import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.Token;
import com.poliymorf.dagaitem.data.enums.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AccountRepository accountRepository;
  private final BiodataRepository biodataRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final CheckSumService checkSumService;

  @Override
  @Transactional
  public Operation register(RegisterRequest request) {
    var user = Account.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .phoneNumber(request.getPhoneNumber())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(RoleEnum.USER)
            .build();

    var biodata = Biodata.builder()
            .name(request.getName())
            .birthDate(TimePars.stringDateToDate(request.getDateBirth()))
            .account(user)
            .experience(BigInteger.ZERO)
            .level(0)
            .build();

    accountRepository.findByEmailOrUsername(request.getEmail(), request.getUsername())
        .ifPresent(account -> {
          throw new BusinessException(GlobalMessage.DATA_ALREADY_EXISTS);
        });

    biodata.setAccount(user);

    Biodata save = biodataRepository.save(biodata);

    // save new sum
    checkSumService.saveNewSum(save.getAccount().getId().toString(), List.of(EnumSum.BIODATA, EnumSum.ACCOUNT));


    return Operation.SUBMITTED;
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var userOption = accountRepository.findByEmail(request.getEmail());

    if(userOption.isEmpty()){
      throw new BusinessException(GlobalMessage.UNAUTHORIZED);
    }

    Account user = userOption.get();

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            request.getPassword()
        )
    );

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(EncryptUtil.encrypt8Bit(jwtToken))
        .refreshToken(EncryptUtil.encrypt8Bit(refreshToken))
        .build();
  }

  private void saveUserToken(Account user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Account user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.accountRepository.findByEmail(userEmail).orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        try {
          new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @Override
  public Operation forgetPassword(AuthenticationRequest request) {
    // TODO MAIL LINK FOR RESET PASSWORD
    return null;
  }

  @Override
  public Operation changePassword(ChangePasswordRequest request) {
    return accountRepository.findByEmail(request.getEmail()).map(data->{
      data.setPassword(request.getNewPassword());
      return Operation.UPDATED;
    }).orElseThrow(()-> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
  }
}
