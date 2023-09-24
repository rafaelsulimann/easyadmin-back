package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.repositories.ProdutoRepository;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = TableName.PRODUTO)
@Data
@NoArgsConstructor
@ToString
public class Produto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public Produto(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository) {
        this.nome = this.validateDuplicidadeNomeProduto(dto, produtoRepository);
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
    }

    public void update(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository) {
        this.validateDuplicidadeNomeProdutoExcludesId(this.id, produtoRepository, dto);
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
    }

    private String validateDuplicidadeNomeProduto(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository) {
        Optional<Produto> entity = produtoRepository.findByNomeIgnoreCase(dto.getNome());
        if(entity.isPresent()) {
            throw new DatabaseException(ExceptionMessage.EXISTS_PRODUTO_BY_NOME);
        } else {
            return dto.getNome();
        }
    }    

    private void validateDuplicidadeNomeProdutoExcludesId(Long id, ProdutoRepository produtoRepository, @Valid ProdutoRequestDTO dto) {
        if(produtoRepository.findByNomeIgnoreCaseExcludesId(id, dto.getNome()).isPresent()){
            throw new DatabaseException(ExceptionMessage.EXISTS_PRODUTO_BY_NOME);
        }
    }

}
