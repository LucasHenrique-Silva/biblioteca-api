package com.leiturasaber.biblioteca_api.api.dto.request;

import com.leiturasaber.biblioteca_api.domain.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {


    @NotBlank(message = "Nome é obrigatorio")
    private String nome;


    private Perfil perfil = Perfil.USUARIO;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    private String senha;
}
