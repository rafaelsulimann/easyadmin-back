package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.easyadmin.easyadmin.models.Produto;
import com.easyadmin.easyadmin.validations.uniqueValue.UniqueValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 100, message = "O nome do produto deve ter de 4 a 100 caracteres")
    @NotBlank(message = "Campo obrigatório")
    @UniqueValue(message = "Já existe um produto com este nome", domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @Positive
    @NotNull(message = "Campo obrigatório")
    private BigDecimal preco;

    private String descricao;

    public Produto convertToEntity(){
        Produto entity = new Produto();
        entity.setNome(this.nome);
        entity.setPreco(this.preco);
        entity.setDescricao(this.descricao);
        return entity;
    }
    
}
