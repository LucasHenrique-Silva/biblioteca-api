package com.leiturasaber.biblioteca_api.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    private String senha;
}
