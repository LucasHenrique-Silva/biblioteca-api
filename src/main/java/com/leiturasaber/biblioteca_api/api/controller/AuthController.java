package com.leiturasaber.biblioteca_api.api.controller;

import com.leiturasaber.biblioteca_api.api.dto.request.AuthRequestDTO;
import com.leiturasaber.biblioteca_api.api.dto.request.RegisterRequestDTO;
import com.leiturasaber.biblioteca_api.api.dto.response.AuthResponseDTO;
import com.leiturasaber.biblioteca_api.domain.model.Perfil;
import com.leiturasaber.biblioteca_api.domain.model.Usuario;
import com.leiturasaber.biblioteca_api.domain.repository.UsuarioRepository;
import com.leiturasaber.biblioteca_api.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")  // ← CORRIGIDO
public class AuthController {

    private final UsuarioRepository userRepo;
    private final AuthenticationManager authenticationManager;  // ← ADICIONADO
    private final PasswordEncoder passwordEncoder;  // ← ADICIONADO
    private final JwtService jwtService;

    @PostMapping("/register")  // ← CORRIGIDO
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) throws Exception {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());  // ← CORRIGIDO

        Usuario user = userRepo.save(Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(senhaCriptografada)
                .perfil(request.getPerfil() != null ? request.getPerfil() : Perfil.USUARIO)  // ← Default USUARIO
                .build());

        String token = jwtService.gerarToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponseDTO(token, user.getEmail(), user.getPerfil().name()));  // ← ADICIONADO
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) throws Exception {
        // Autentica o usuário
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Usuario usuario = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email não cadastrado"));

        String token = jwtService.gerarToken(usuario.getEmail());

        return ResponseEntity.ok(new AuthResponseDTO(token, usuario.getEmail(), usuario.getPerfil().name()));
    }
}