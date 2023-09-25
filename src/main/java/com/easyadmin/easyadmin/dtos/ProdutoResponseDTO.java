package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;

import com.easyadmin.easyadmin.models.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private List<CategoriaResponseDTO> categorias = new ArrayList<>();

    public ProdutoResponseDTO(Produto entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.descricao = entity.getDescricao();
        this.categorias = this.extractCategorias(entity);
    }

    private List<CategoriaResponseDTO> extractCategorias(Produto entity) {
        try {
            return entity.getCategorias().stream().map(categoria -> new CategoriaResponseDTO(categoria)).toList();
        } catch (LazyInitializationException e) {
            return null;
        }
    }

}
