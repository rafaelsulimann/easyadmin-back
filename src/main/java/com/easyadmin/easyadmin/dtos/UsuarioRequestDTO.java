package com.easyadmin.easyadmin.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.easyadmin.easyadmin.validations.UsernameWithoutSpace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 100, message = "O username deve ter entre 4 a 100 caracteres")
    @NotBlank(message = "Campo obrigatório")
    @UsernameWithoutSpace(message = "Username não pode conter espaços")
    private String username;

    @NotBlank(message = "Campo obrigatório")
    private String password;
    
}
