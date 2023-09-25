package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCategoriaRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Campo obrigatório")
    private Long id;
    
}
