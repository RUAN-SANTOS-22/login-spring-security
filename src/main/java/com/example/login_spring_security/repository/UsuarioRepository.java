package com.example.login_spring_security.repository;

import com.example.login_spring_security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameAndIsActive(String username, Boolean isActive);
}
