package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;

import com.easyadmin.easyadmin.models.Venda;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendaResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime momento;
    private List<ItemVendaResponseDTO> itens = new ArrayList<>();
    private String usuario;
    private String tipoPagamento;
    private BigDecimal total;

    public VendaResponseDTO(Venda entity){
        id = entity.getId();
        momento = entity.getMomento();
        itens = this.extractItensVenda(entity);
        usuario = this.getUsuario(entity);
        tipoPagamento = this.getTipoPagamento(entity);
        total = entity.getTotal();
    }

    private String getUsuario(Venda entity) {
        return new UsuarioResponseDTO(entity.getUsuario()).getUsername();
    }

    private String getTipoPagamento(Venda entity) {
        return new PagamentoVendaResponseDTO(entity.getPagamento()).getTipo().toString();
    }

    private List<ItemVendaResponseDTO> extractItensVenda(Venda entity) {
        try {
            return entity.getItens().stream().map(itemEntity -> new ItemVendaResponseDTO(itemEntity)).toList();
        } catch (LazyInitializationException e) {
            return null;
        }
    }

}
