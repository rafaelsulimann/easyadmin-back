package com.easyadmin.easyadmin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.easyadmin.easyadmin.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Usuario> findByUsernameIgnoreCase(String username);

    Optional<Usuario> findByUsername(String username);
    
}
