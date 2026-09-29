package com.metadiaria.api.service;


import com.metadiaria.api.dto.AuthResponseDTO;
import com.metadiaria.api.dto.LoginRequestDTO;
import com.metadiaria.api.dto.RegistroUsuarioRequestDTO;
import com.metadiaria.api.entity.Usuario;
import com.metadiaria.api.repository.UsuarioRepository;
import com.metadiaria.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;


    public AuthResponseDTO registrar(RegistroUsuarioRequestDTO dto) {
        if(usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setHorarioNotificacao(dto.getHorarioNotificacao());

        usuario = usuarioRepository.save(usuario);

        String token = tokenProvider.gerarToken(usuario.getEmail());
        return new AuthResponseDTO(token, usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos."));

        // Compara a senha informada com o hash salvo no banco
        if(!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        String token = tokenProvider.gerarToken(usuario.getEmail());
        return new AuthResponseDTO(token, usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
