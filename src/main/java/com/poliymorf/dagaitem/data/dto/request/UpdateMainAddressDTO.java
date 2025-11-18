package com.poliymorf.dagaitem.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMainAddressDTO {
    public String id;
    public Boolean mainAddress;
}
