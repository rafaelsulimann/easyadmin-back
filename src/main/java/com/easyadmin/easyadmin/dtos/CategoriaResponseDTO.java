package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import com.easyadmin.easyadmin.models.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public CategoriaResponseDTO(Categoria entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
    
}
