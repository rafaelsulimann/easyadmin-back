package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
    
    public Categoria(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeNomeCategoria(dto, categoriaRepository);
        this.nome = dto.getNome();
    }

    public void update(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeNomeCategoriaExcludesId(this.id, categoriaRepository, dto);
        this.nome = dto.getNome();
    }

    private void validateDuplicidadeNomeCategoria(@Valid CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = this.findByNomeIgnoreCase(dto, categoriaRepository);
        if(categoria.isPresent()) {
            throw new DatabaseException(ExceptionMessage.EXISTS_CATEGORIA_BY_NOME);
        }
    }

    private void validateDuplicidadeNomeCategoriaExcludesId(Long id, CategoriaRepository categoriaRepository, @Valid CategoriaRequestDTO dto) {
         Optional<Categoria> categoria = this.findByNomeIgnoreCase(dto, categoriaRepository);
        if(categoria.isPresent()){
            if(categoria.get().getId() != id){
                throw new DatabaseException(ExceptionMessage.EXISTS_CATEGORIA_BY_NOME);
            }
        }
    }

    private Optional<Categoria> findByNomeIgnoreCase(CategoriaRequestDTO dto, CategoriaRepository categoriaRepository) {
        return categoriaRepository.findByNomeIgnoreCase(dto.getNome());
    }


}
