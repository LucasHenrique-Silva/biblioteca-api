package com.leiturasaber.biblioteca_api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    @Column(unique = true)
    private String email;

    private String senha;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
}
