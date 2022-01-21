package com.ptechnology.aiauthservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseMessage {

    String accessToken;
    String refreshToken;
}
