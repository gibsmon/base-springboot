package com.poliymorf.dagaitem.controller.user;


import com.poliymorf.dagaitem.config.JwtService;
import com.poliymorf.dagaitem.data.dto.request.RequestAccountAddressDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountAddresReponseDTO;
import com.poliymorf.dagaitem.data.dto.response.AccountResponseDto;
import com.poliymorf.dagaitem.data.enums.Operation;
import com.poliymorf.dagaitem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poliymorf.dagaitem.data.constanta.EndPoint.VERSION1;

@RestController
@RequestMapping(VERSION1+"/user")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final AccountService accountService;

    @GetMapping(value = "/my-profile/{sum}")
    public AccountResponseDto getProfileCtr(@RequestHeader("Authorization") String authHeader, @PathVariable("sum") String sum) {
        return accountService.getProfileSrv(jwtService.getheader(authHeader), sum);
    }

    @PostMapping(value = "/address/")
    public Operation addAddressCtr(@RequestHeader("Authorization") String authHeader, @RequestBody RequestAccountAddressDTO body) {
        return accountService.addAddressSrv(jwtService.getheader(authHeader), body);
    }

    @GetMapping(value = "/address/{sum}")
    public List<AccountAddresReponseDTO> getAddressCtr(@RequestHeader("Authorization") String authHeader, @PathVariable("sum") String sum) {
        return accountService.getAddressSrv(jwtService.getheader(authHeader), sum);
    }

    @PutMapping(value = "/address/")
    public Operation putAddressCtr(@RequestHeader("Authorization") String authHeader, @RequestBody RequestAccountAddressDTO body) {
        return accountService.putAddressSrv(jwtService.getheader(authHeader), body);
    }


}
