package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.easyadmin.easyadmin.models.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    public ProdutoResponseDTO(Produto entity){
        id = entity.getId();
        nome = entity.getNome();
        preco = entity.getPreco();
        descricao = entity.getDescricao();
    }
    
}
