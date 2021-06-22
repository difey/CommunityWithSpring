package com.example.demo.DTO;

import lombok.Data;

@Data
public class GoogleIDTokenDTO {
    private String access_token;
    private String expires_in;
    private String id_token;
    private String scope;
    private String token_type;
    private String refresh_token;
}
