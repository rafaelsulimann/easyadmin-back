package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long produtoId;
    private Integer quantidade;

}
