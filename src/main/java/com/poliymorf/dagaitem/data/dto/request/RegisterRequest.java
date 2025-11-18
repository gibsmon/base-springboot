package com.poliymorf.dagaitem.data.dto.request;

import com.poliymorf.dagaitem.data.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String username;
  private String email;
  private String phoneNumber;
  private String password;
  private String name;
  private String dateBirth;
  private RoleEnum role;
}
