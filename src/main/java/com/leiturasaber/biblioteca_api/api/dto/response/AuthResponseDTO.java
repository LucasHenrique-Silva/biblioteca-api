package com.leiturasaber.biblioteca_api.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String email;
    private String perfil;
}
