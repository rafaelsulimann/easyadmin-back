package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.easyadmin.easyadmin.models.ItemVenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal subTotal;

    public ItemVendaResponseDTO(ItemVenda entity) {
        id = entity.getId().getProduto().getId();
        nome = entity.getId().getProduto().getNome();
        quantidade = entity.getQuantidade();
        preco = entity.getId().getProduto().getPreco();
        subTotal = entity.getId().getProduto().getPreco().multiply(new BigDecimal(quantidade));
    }
    
}
