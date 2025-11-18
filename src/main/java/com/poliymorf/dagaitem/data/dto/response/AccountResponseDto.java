package com.poliymorf.dagaitem.data.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    public String id;
    public String username;
    public String email;
    public String phoneNumber;
    public String sum;
    public BiodataResponseDto biodata;
}
