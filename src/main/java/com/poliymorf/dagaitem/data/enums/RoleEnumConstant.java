package com.poliymorf.dagaitem.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.poliymorf.dagaitem.data.enums.Permission.*;


@RequiredArgsConstructor
public enum RoleEnumConstant {

  GUEST,
  ADMIN,
  USER


}
