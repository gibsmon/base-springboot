package com.poliymorf.dagaitem.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddresReponseDTO {
    public String id;
    public String address;
    public Boolean mainAddress;
    public String sum;
}
