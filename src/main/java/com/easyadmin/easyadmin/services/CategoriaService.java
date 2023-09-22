package com.easyadmin.easyadmin.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyadmin.easyadmin.dtos.CategoriaRequestDTO;
import com.easyadmin.easyadmin.dtos.CategoriaResponseDTO;
import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.repositories.CategoriaRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<CategoriaResponseDTO> listarTodasCategoria(Specification<Categoria> spec, Pageable pageable){
        log.info("Listando todas categorias...");
        Page<Categoria> listaCategorias = this.categoriaRepository.findAll(spec, pageable);
        log.info("Categorias retornadas: {}", listaCategorias);
        return listaCategorias.map(categoria -> new CategoriaResponseDTO(categoria));
    }

    public CategoriaResponseDTO inserirCategoria(@Valid CategoriaRequestDTO dto) {
        log.info("Request inserirCategoria, request: {}", dto);
        Categoria entity = dto.convertToEntity();
        entity = this.categoriaRepository.save(entity);
        log.info("Categoria {} salva com sucesso", entity);
        return new CategoriaResponseDTO(entity);
    }
}
