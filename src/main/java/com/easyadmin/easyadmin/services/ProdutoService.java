package com.easyadmin.easyadmin.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.dtos.ProdutoResponseDTO;
import com.easyadmin.easyadmin.models.Produto;
import com.easyadmin.easyadmin.repositories.CategoriaRepository;
import com.easyadmin.easyadmin.repositories.ProdutoRepository;
import com.easyadmin.easyadmin.services.exceptions.ResourceNotFoundException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<ProdutoResponseDTO> findAll(Specification<Produto> spec, Pageable pageable) {
        Page<Produto> searchReturn = this.produtoRepository.findAll(spec, pageable);
        searchReturn.forEach(entity -> entity.setCategorias(this.categoriaRepository.findAllCategoriasByProduto(entity.getId())));
        return searchReturn.map(entity -> new ProdutoResponseDTO(entity));
    }

    private Produto findProdutoById(Long id){
        return this.produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.PRODUTO_NOT_FOUND));
    }

    public ProdutoResponseDTO findById(Long id){
        Produto entity = this.findProdutoById(id);
        entity.setCategorias(this.categoriaRepository.findAllCategoriasByProduto(id));
        return new ProdutoResponseDTO(entity);
    }

    public ProdutoResponseDTO insert(@Valid ProdutoRequestDTO dto) {
        Produto entity = new Produto(dto, this.produtoRepository, this.categoriaRepository);
        entity = this.produtoRepository.save(entity);
        return new ProdutoResponseDTO(entity);
    }

    public ProdutoResponseDTO update(Long id, @Valid ProdutoRequestDTO dto) {
        Produto entity = this.findProdutoById(id);
        entity.update(dto, this.produtoRepository, this.categoriaRepository);
        entity = this.produtoRepository.save(entity);
        return new ProdutoResponseDTO(entity);
    }

    public void delete(Long id){
        try {
            this.produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ExceptionMessage.PRODUTO_NOT_FOUND);
        }
    }

}
