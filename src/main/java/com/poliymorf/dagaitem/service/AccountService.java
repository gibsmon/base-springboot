package com.poliymorf.dagaitem.service;

import com.poliymorf.dagaitem.data.dto.request.RequestAccountAddressDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountAddresReponseDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountResponseDto;
import com.poliymorf.dagaitem.data.dto.response.JwtTokenResponse;
import com.poliymorf.dagaitem.data.enums.Operation;

import java.util.List;

public interface AccountService {

  AccountResponseDto getProfileSrv(JwtTokenResponse authHeader, String sum);

    Operation addAddressSrv(JwtTokenResponse getheader, RequestAccountAddressDTO body);

    List<AccountAddresReponseDTO> getAddressSrv(JwtTokenResponse getheader, String sum);

  Operation putAddressSrv(JwtTokenResponse getheader, RequestAccountAddressDTO body);
}
