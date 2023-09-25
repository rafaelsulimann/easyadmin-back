package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 100, message = "O nome do produto deve ter entre 4 a 100 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Positive(message = "O preço deve ser maior do que 0")
    @NotNull(message = "Campo obrigatório")
    private BigDecimal preco;

    private String descricao;

    @NotEmpty(message = "Deve-se informar no mínimo uma categoria")
    private List<ProdutoCategoriaRequestDTO> categorias = new ArrayList<>();

}
