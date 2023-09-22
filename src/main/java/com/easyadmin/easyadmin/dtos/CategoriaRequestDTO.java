package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.validations.uniqueValue.UniqueValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 100, message = "O nome da categoria deve conter de 4 a 100 caracteres")
    @NotBlank(message = "Campo obrigatório")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome de categoria já existente")
    private String nome;

    public Categoria convertToEntity() {
        Categoria entity = new Categoria();
        entity.setNome(this.nome);
        return entity;
    }
    
}
