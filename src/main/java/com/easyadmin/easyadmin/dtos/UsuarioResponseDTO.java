package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import com.easyadmin.easyadmin.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;

    public UsuarioResponseDTO(Usuario entity) {
        id = entity.getId();
        username = entity.getUsername();
    }
    
}
