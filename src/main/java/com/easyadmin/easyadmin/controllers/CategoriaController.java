package com.easyadmin.easyadmin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyadmin.easyadmin.dtos.CategoriaRequestDTO;
import com.easyadmin.easyadmin.dtos.CategoriaResponseDTO;
import com.easyadmin.easyadmin.services.CategoriaService;
import com.easyadmin.easyadmin.specifications.SpecificationTemplate;
import com.easyadmin.easyadmin.utils.constraints.ResponseMessage;
import com.easyadmin.easyadmin.utils.constraints.URI;

@RestController
@RequestMapping(value = URI.CATEGORIA)
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<Page<CategoriaResponseDTO>> findAll(SpecificationTemplate.CategoriaSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.findAll(spec, pageable));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<CategoriaResponseDTO> insert(@RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.CATEGORIA_DELETED);
    }
    
}
