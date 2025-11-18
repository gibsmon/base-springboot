package com.poliymorf.dagaitem.config;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.data.dto.response.JwtTokenResponse;
import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.util.EncryptUtil;
import com.poliymorf.dagaitem.util.Mapper;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public JwtTokenResponse getheader(String token) {
    try{
      String newToken = EncryptUtil.decrypt8Bit(token.substring(7));

      Jws<Claims> jwsClaims = Jwts
              .parser()
              .setSigningKey(getSignInKey())
              .parseClaimsJws(newToken);

      return Mapper.jsonToObj(jwsClaims.getBody(), JwtTokenResponse.class);
    }catch (Exception e){
      log.error(e.getMessage());
      throw new BusinessException(GlobalMessage.UNAUTHORIZED);
    }
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(Account userDetails) {
    return buildToken(createClaims(userDetails), userDetails, jwtExpiration);
  }

  private HashMap<String, Object> createClaims(Account userDetails) {
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("userId", userDetails.getId());
    claims.put("username", userDetails.getUsername());
    claims.put("email", userDetails.getEmail());
    claims.put("phoneNumber", userDetails.getPhoneNumber());
    claims.put("timeStamp", new Date().getTime());
    return claims;
  }

  public String generateRefreshToken(
          Account userDetails
  ) {
    return buildToken(createClaims(userDetails), userDetails, refreshExpiration);
  }

  private String buildToken(
          Map<String, Object> extraClaims,
          Account userDetails,
          long expiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
