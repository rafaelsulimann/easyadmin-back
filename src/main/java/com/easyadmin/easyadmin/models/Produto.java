package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.repositories.CategoriaRepository;
import com.easyadmin.easyadmin.repositories.ProdutoRepository;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.services.exceptions.ResourceNotFoundException;
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

    @ManyToMany
    @JoinTable(name = TableName.PRODUTO_CATEGORIA, joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    public Produto(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeNomeProduto(dto, produtoRepository);
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
        this.insertCategorias(dto, categoriaRepository);
    }

    public void update(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeNomeProdutoExcludesId(this.id, produtoRepository, dto);
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
        this.updateCategorias(dto, categoriaRepository);
    }

    private void insertCategorias(@Valid ProdutoRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeCategorias(dto);
        dto.getCategorias().stream().forEach(categoriaDto -> {
            Categoria categoria = categoriaRepository.findById(categoriaDto.getId()).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.CATEGORIA_NOT_FOUND));
            this.categorias.add(categoria);
        });
    }

    private void updateCategorias(@Valid ProdutoRequestDTO dto, CategoriaRepository categoriaRepository) {
        this.validateDuplicidadeCategorias(dto);
        this.deleteAllCategorias();
        dto.getCategorias().stream().forEach(categoriaDto -> {
            Categoria categoria = categoriaRepository.findById(categoriaDto.getId()).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.CATEGORIA_NOT_FOUND));
            this.categorias.add(categoria);
        });
    }

    private void validateDuplicidadeNomeProduto(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository) {
        Optional<Produto> entity = this.findByNomeIgnoreCase(dto, produtoRepository);
        if(entity.isPresent()) {
            throw new DatabaseException(ExceptionMessage.EXISTS_PRODUTO_BY_NOME);
        }
    }    

    private void validateDuplicidadeNomeProdutoExcludesId(Long id, ProdutoRepository produtoRepository, @Valid ProdutoRequestDTO dto) {
        Optional<Produto> entity = this.findByNomeIgnoreCase(dto, produtoRepository);
        if(entity.isPresent()){
            if(entity.get().getId() != id){
                throw new DatabaseException(ExceptionMessage.EXISTS_PRODUTO_BY_NOME);
            }
        }
    }

    private Optional<Produto> findByNomeIgnoreCase(@Valid ProdutoRequestDTO dto, ProdutoRepository produtoRepository){
        return produtoRepository.findByNomeIgnoreCase(dto.getNome());
    }

    private void deleteAllCategorias(){
        this.setCategorias(new ArrayList<>());
    }

    private void validateDuplicidadeCategorias(@Valid ProdutoRequestDTO dto) {
        Set<Long> categoriasUnicas = dto.getCategorias().stream().map(categoriaDTO -> categoriaDTO.getId()).collect(Collectors.toSet());
        if (categoriasUnicas.size() != dto.getCategorias().size()) {
            throw new DatabaseException("Existem categorias duplicadas na lista enviada");
        }
    }

}
