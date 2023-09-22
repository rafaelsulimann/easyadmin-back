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

    private Long productId;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    public ProdutoResponseDTO(Produto entity){
        this.productId = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.descricao = entity.getDescricao();
    }
    
}
