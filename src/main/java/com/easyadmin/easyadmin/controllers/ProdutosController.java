package com.easyadmin.easyadmin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.dtos.ProdutoResponseDTO;
import com.easyadmin.easyadmin.services.ProdutoService;
import com.easyadmin.easyadmin.specifications.SpecificationTemplate;
import com.easyadmin.easyadmin.utils.constraints.URI;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = URI.PRODUTO)
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(SpecificationTemplate.ProdutoSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.listarTodosProdutos(spec, pageable));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> inserirProduto(@RequestBody @Valid ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.inserirProduto(dto));
    }

}
