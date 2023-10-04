package com.easyadmin.easyadmin.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easyadmin.easyadmin.dtos.VendaRequestDTO;
import com.easyadmin.easyadmin.dtos.VendaResponseDTO;
import com.easyadmin.easyadmin.models.Venda;
import com.easyadmin.easyadmin.repositories.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<VendaResponseDTO> findAll(Specification<Venda> spec, Pageable pageable){
        Page<Venda> vendas = this.vendaRepository.findAll(spec, pageable);
        return vendas.map(venda -> new VendaResponseDTO(venda));
    }

    public VendaResponseDTO insert(@Valid VendaRequestDTO dto) {
        Venda entity = new Venda(dto, this.usuarioService, this.produtoService);
        entity = this.vendaRepository.save(entity);
        return new VendaResponseDTO(entity);
    }
    
}
