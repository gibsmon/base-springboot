package com.poliymorf.dagaitem.data.dto.response;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.AccountAddress;
import com.poliymorf.dagaitem.data.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BiodataResponseDto  {

  public String id;
  public String name;
  public Date birthDate;


}
