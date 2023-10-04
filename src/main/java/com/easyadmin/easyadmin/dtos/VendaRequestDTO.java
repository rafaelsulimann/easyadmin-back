package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.easyadmin.easyadmin.models.enums.TipoPagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Deve-se informar no mínimo um item")
    private List<ItemVendaRequestDTO> itens;
    private TipoPagamento tipoPagamento;
    
}
