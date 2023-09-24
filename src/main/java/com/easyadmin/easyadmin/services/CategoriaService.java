package com.easyadmin.easyadmin.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easyadmin.easyadmin.dtos.CategoriaRequestDTO;
import com.easyadmin.easyadmin.dtos.CategoriaResponseDTO;
import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.repositories.CategoriaRepository;
import com.easyadmin.easyadmin.services.exceptions.ResourceNotFoundException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<CategoriaResponseDTO> findAll(Specification<Categoria> spec, Pageable pageable) {
        Page<Categoria> searchResult = this.categoriaRepository.findAll(spec, pageable);
        Page<CategoriaResponseDTO> response = searchResult.map(categoria -> new CategoriaResponseDTO(categoria));
        return response;
    }

    public CategoriaResponseDTO insert (@Valid CategoriaRequestDTO dto){
        Categoria entity = new Categoria(dto, this.categoriaRepository);
        entity = this.categoriaRepository.save(entity);
        return new CategoriaResponseDTO(entity);
    }

    public CategoriaResponseDTO update(Long id, @Valid CategoriaRequestDTO dto) {
        Categoria entity = this.findCategoriaById(id);
        entity.update(dto, this.categoriaRepository);
        entity = this.categoriaRepository.save(entity);
        return new CategoriaResponseDTO(entity);
    }

    private Categoria findCategoriaById(Long id) {
        return this.categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.CATEGORIA_NOT_FOUND));
    }

    public void delete(Long id) {
        try {
            this.categoriaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ExceptionMessage.CATEGORIA_NOT_FOUND);
        }
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria entity = this.findCategoriaById(id);
        return new CategoriaResponseDTO(entity);
    }
    
}
