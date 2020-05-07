package com.gitmuts.openbanking.model;

import lombok.Data;

@Data
public class GetTokenResponse {

    private String access_token;
    private String scope;
    private String token_type;
    private String expires_in;
}
