package com.easyadmin.easyadmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.dtos.ProdutoResponseDTO;
import com.easyadmin.easyadmin.models.Produto;
import com.easyadmin.easyadmin.repositories.ProdutoRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public Page<ProdutoResponseDTO> listarTodosProdutos(Specification<Produto> spec, Pageable pageable) {
        log.info("Listando todos os produtos...");
        Page<ProdutoResponseDTO> response = this.produtoRepository.findAll(spec, pageable)
                .map(produto -> new ProdutoResponseDTO(produto));
        log.info("Produtos: {}", response.getContent().toString());
        return response;
    }

    @Transactional
    public ProdutoResponseDTO inserirProduto(ProdutoRequestDTO dto) {
        log.info("Chamando método: inserirProduto. ProdutoRequestDTO: {}", dto);
        Produto entity = dto.convertToEntity();
        entity = this.produtoRepository.save(entity);
        log.info("Produto {} salvo com sucesso!", entity.toString());
        return new ProdutoResponseDTO(entity);
    }

}
