package com.poliymorf.dagaitem.util;

import com.poliymorf.dagaitem.config.JwtService;
import com.poliymorf.dagaitem.data.dto.response.JwtTokenResponse;

public class JwtHandler {

    public static JwtTokenResponse getHeader(String authHeader) {
        JwtService jw = new JwtService();
        return jw.getheader(authHeader);
    }
}
