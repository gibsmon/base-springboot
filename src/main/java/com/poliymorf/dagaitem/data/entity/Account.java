package com.poliymorf.dagaitem.data.entity;


import com.poliymorf.dagaitem.data.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "m_account")
public class Account extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue
  public Integer id;

  @Column(name = "username", unique = true)
  public String username;

  @Column(name = "email", unique = true)
  public String email;

  @Column(name = "phoneNumber", unique = true)
  public String phoneNumber;

  @Column(name = "password")
  public String password;

  @Column(name = "attempt_failed_login")
  public Integer attemptFailedLogin;

  @Enumerated(EnumType.STRING)
  public RoleEnum role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

//  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//  public List<Token> tokens;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
