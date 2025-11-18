package com.poliymorf.dagaitem.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenResponse{
	private String timeStamp;
	private String sub;
	private String phoneNumber;
	private String exp;
	private String userId;
	private String iat;
	private String email;
	private String username;


}