package com.example.login_spring_security.controller;

import com.example.login_spring_security.entity.Usuario;
import com.example.login_spring_security.repository.UsuarioRepository;
import com.example.login_spring_security.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired 
    private JwtService jwtService;

    @PostMapping("/encodedPassword")
    public void saveUserWithEncodedPassword(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setIsActive(true);
        usuario.setRole(role);

        usuarioRepository.save(usuario);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {

        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
            )
        );

        if (authenticate.isAuthenticated()) {
            String role = authenticate
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");
            return jwtService.generateToken(authRequest.getUsername(), role);
        }

        return null;
    }
}
