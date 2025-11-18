package com.poliymorf.dagaitem.data.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poliymorf.dagaitem.data.dto.response.BiodataResponseDto;
import com.poliymorf.dagaitem.data.entity.BaseEntity;
import com.poliymorf.dagaitem.data.entity.Biodata;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAccountAddressDTO  {
  private String id;
  private String address;
  private Boolean mainAddress;

}
