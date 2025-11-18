package com.poliymorf.dagaitem.data.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeaderBaseRequest implements Serializable {

    private String customerType;
    private Long corporateId;
    private String corporateCode;
    private String phoneNumber;
    private String notificationToken;
    private String zoneOffset;

}
