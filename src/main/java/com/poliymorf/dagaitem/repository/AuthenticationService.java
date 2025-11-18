package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.dto.request.AuthenticationRequest;
import com.poliymorf.dagaitem.data.dto.request.ChangePasswordRequest;
import com.poliymorf.dagaitem.data.dto.request.RegisterRequest;
import com.poliymorf.dagaitem.data.dto.response.AuthenticationResponse;
import com.poliymorf.dagaitem.data.enums.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
  Operation register(RegisterRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);

  void refreshToken(HttpServletRequest request, HttpServletResponse response);

  Operation forgetPassword(AuthenticationRequest request);

  Operation changePassword(ChangePasswordRequest request);
}
