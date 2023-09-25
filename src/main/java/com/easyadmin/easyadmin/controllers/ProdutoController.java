package com.easyadmin.easyadmin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyadmin.easyadmin.dtos.ProdutoRequestDTO;
import com.easyadmin.easyadmin.dtos.ProdutoResponseDTO;
import com.easyadmin.easyadmin.services.ProdutoService;
import com.easyadmin.easyadmin.specifications.SpecificationTemplate;
import com.easyadmin.easyadmin.utils.constraints.ResponseMessage;
import com.easyadmin.easyadmin.utils.constraints.URI;

@RestController
@RequestMapping(value = URI.PRODUTO)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> findAll(SpecificationTemplate.ProdutoSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.findAll(spec, pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insert(@RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.PRODUTO_DELETED);
    }

}
