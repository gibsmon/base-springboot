package com.poliymorf.dagaitem.service.impl;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.data.dto.request.RequestAccountAddressDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountAddresReponseDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountResponseDto;
import com.poliymorf.dagaitem.data.dto.response.JwtTokenResponse;
import com.poliymorf.dagaitem.data.dto.response.BiodataResponseDto;
import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.AccountAddress;
import com.poliymorf.dagaitem.data.entity.Biodata;
import com.poliymorf.dagaitem.data.enums.EnumSum;
import com.poliymorf.dagaitem.data.enums.Operation;
import com.poliymorf.dagaitem.repository.AccountAddressRepository;
import com.poliymorf.dagaitem.repository.AccountRepository;
import com.poliymorf.dagaitem.repository.BiodataRepository;
import com.poliymorf.dagaitem.service.AccountService;
import com.poliymorf.dagaitem.service.CheckSumService;
import com.poliymorf.dagaitem.util.EncryptUtil;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BiodataRepository biodataRepository;
    private final AccountAddressRepository accountAddressRepository;
    private final CheckSumService checkSumService;

    @Override
    public AccountResponseDto getProfileSrv(JwtTokenResponse authHeader, String feSum) {
        // Validate SUM
        var checkSum = checkSumService.validateCheckSumProfile(authHeader.getUserId(), feSum, EnumSum.PROFILE);
        // Get Account
        var accountResp = accountRepository.findByUsername(authHeader.getUsername()).orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));

        var accountResponseDto =  AccountResponseDto.builder()
                .id(EncryptUtil.encrypt8Bit(accountResp.getId().toString()))
                .username(accountResp.getUsername())
                .email(accountResp.getEmail())
                .phoneNumber(accountResp.getPhoneNumber())
                .sum(checkSum)
                .build();

        var byAccount = biodataRepository.findByAccount(accountResp);
        if(byAccount.isPresent()){
            var biodataResponseDto = BiodataResponseDto.builder()
                    .birthDate(byAccount.get().getBirthDate())
                    .id(byAccount.get().getId())
                    .name(byAccount.get().getName())
                    .build();
            accountResponseDto.setBiodata(biodataResponseDto);
        }

        return accountResponseDto;
    }

    @Override
    public Operation addAddressSrv(JwtTokenResponse getheader, RequestAccountAddressDTO body) {
        var biodata = biodataRepository.findByAccount_Id(Integer.parseInt(getheader.getUserId())).orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));

        var accountAddress = AccountAddress.builder()
                .address(body.getAddress())
                .mainAddress(false)
                .biodata(biodata)
                .build();

        biodata.getListAddress().add(accountAddress);

        biodataRepository.save(biodata);

        // update sum address
        checkSumService.saveNewSum(getheader.getUserId(), List.of(EnumSum.ADDRESS));

        return Operation.SUBMITTED;
    }

    @Override
    public List<AccountAddresReponseDTO> getAddressSrv(JwtTokenResponse getheader, String feSum) {

        // Validate SUM
        var checkSum = checkSumService.validateCheckSumProfile(getheader.getUserId(), feSum, EnumSum.ADDRESS);

        // Get Address
        var biodata = biodataRepository.findByAccount_Id(Integer.parseInt(getheader.getUserId())).orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));

        var collect = biodata.getListAddress().stream().map(accountAddress -> AccountAddresReponseDTO.builder()
                .address(accountAddress.getAddress())
                .mainAddress(accountAddress.getMainAddress())
                .id(accountAddress.getId())
                .sum(checkSum)
                .build()).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Operation putAddressSrv(JwtTokenResponse getheader, RequestAccountAddressDTO body) {

        var accountAddress = accountAddressRepository.findById(body.getId()).orElseThrow(() -> new BusinessException(GlobalMessage.DATA_NOT_FOUND));
        accountAddress.setAddress(body.getAddress());
        accountAddress.setMainAddress(body.getMainAddress());
        accountAddressRepository.save(accountAddress);

        if(body.getMainAddress()){
            accountAddressRepository.updateMainAddress(body.getId());
        }

        checkSumService.saveNewSum(getheader.getUserId(), List.of(EnumSum.ADDRESS));
        return Operation.UPDATED;
    }
}
