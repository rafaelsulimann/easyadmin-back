package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import com.easyadmin.easyadmin.models.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long categoriaId;
    private String nome;

    public CategoriaResponseDTO(Categoria entity){
        this.categoriaId = entity.getId();
        this.nome = entity.getNome();
    }
    
}
