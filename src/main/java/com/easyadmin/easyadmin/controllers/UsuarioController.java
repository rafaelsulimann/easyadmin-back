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

import com.easyadmin.easyadmin.dtos.UsuarioRequestDTO;
import com.easyadmin.easyadmin.dtos.UsuarioResponseDTO;
import com.easyadmin.easyadmin.services.UsuarioService;
import com.easyadmin.easyadmin.specifications.SpecificationTemplate;
import com.easyadmin.easyadmin.utils.constraints.ResponseMessage;
import com.easyadmin.easyadmin.utils.constraints.URI;

@RestController
@RequestMapping(value = URI.USUARIO)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UsuarioResponseDTO>> findAll(SpecificationTemplate.UsuarioSpec spec,
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
           return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findAll(spec, pageable)); 
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> insert(@RequestBody @Valid UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.USUARIO_DELETED);
    }
    
}
