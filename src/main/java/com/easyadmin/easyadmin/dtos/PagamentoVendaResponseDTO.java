package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import com.easyadmin.easyadmin.models.Pagamento;
import com.easyadmin.easyadmin.models.enums.TipoPagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoVendaResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private TipoPagamento tipo;

    public PagamentoVendaResponseDTO(Pagamento entity){
        tipo = entity.getTipoPagamento();
    }
}
