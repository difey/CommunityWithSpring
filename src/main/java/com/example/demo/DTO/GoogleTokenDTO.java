package com.example.demo.DTO;

import lombok.Data;

@Data
public class GoogleTokenDTO {
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String grant_type;
}
