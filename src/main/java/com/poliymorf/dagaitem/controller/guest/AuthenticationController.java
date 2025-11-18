package com.poliymorf.dagaitem.controller.guest;

import com.poliymorf.dagaitem.data.dto.request.AuthenticationRequest;
import com.poliymorf.dagaitem.data.dto.request.ChangePasswordRequest;
import com.poliymorf.dagaitem.data.dto.request.RegisterRequest;
import com.poliymorf.dagaitem.data.dto.response.AuthenticationResponse;
import com.poliymorf.dagaitem.data.enums.Operation;
import com.poliymorf.dagaitem.repository.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.poliymorf.dagaitem.data.constanta.EndPoint.VERSION1;

@RestController
@RequestMapping(VERSION1+"/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public Operation register(
      @RequestBody RegisterRequest request
  ) {
    return service.register(request);
  }
  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }

  @PostMapping("/forget-password")
  public Operation forgetPassword(
          @RequestBody AuthenticationRequest request
  ) {
    return service.forgetPassword(request);
  }

  @PostMapping("/change-password")
  public Operation changePassword(
          @RequestBody ChangePasswordRequest request
  ) {
    return service.changePassword(request);
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    service.refreshToken(request, response);
  }


}
