package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.easyadmin.easyadmin.dtos.CategoriaRequestDTO;
import com.easyadmin.easyadmin.repositories.CategoriaRepository;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = TableName.CATEGORIA)
@Data
@NoArgsConstructor
@ToString
public class Categoria implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, unique = true)
    private String nome;
    
    public Categoria(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.nome = this.validateDuplicidadeNomeCategoria(dto, categoriaRepository);
    }

    public void update(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeNomeCategoriaExcludesId(this.id, categoriaRepository, dto);
        this.nome = dto.getNome();
    }

    private String validateDuplicidadeNomeCategoria(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        Optional<Categoria> entity = categoriaRepository.findByNomeIgnoreCase(dto.getNome());
        if(entity.isPresent()) {
            throw new DatabaseException(ExceptionMessage.EXISTS_CATEGORIA_BY_NOME);
        } else {
            return dto.getNome();
        }
    }

    private void validateDuplicidadeNomeCategoriaExcludesId(Long id, CategoriaRepository categoriaRepository, @Valid CategoriaRequestDTO dto) {
        if(categoriaRepository.findByNomeIgnoreCaseExcludesId(id, dto.getNome()).isPresent()){
            throw new DatabaseException(ExceptionMessage.EXISTS_CATEGORIA_BY_NOME);
        }
    }

}
