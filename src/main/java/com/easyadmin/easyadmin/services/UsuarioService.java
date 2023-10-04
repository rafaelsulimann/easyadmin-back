package com.easyadmin.easyadmin.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easyadmin.easyadmin.dtos.UsuarioRequestDTO;
import com.easyadmin.easyadmin.dtos.UsuarioResponseDTO;
import com.easyadmin.easyadmin.models.Usuario;
import com.easyadmin.easyadmin.repositories.UsuarioRepository;
import com.easyadmin.easyadmin.services.exceptions.ResourceNotFoundException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;

@Service
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponseDTO> findAll(Specification<Usuario> spec, Pageable pageable) {
        Page<Usuario> usuarios = this.usuarioRepository.findAll(spec, pageable);
        return usuarios.map(entity -> new UsuarioResponseDTO(entity));
    }

    public UsuarioResponseDTO insert(@Valid UsuarioRequestDTO dto) {
        Usuario entity = new Usuario(dto, this.usuarioRepository);
        entity = this.usuarioRepository.save(entity);
        return new UsuarioResponseDTO(entity);
    }

    public UsuarioResponseDTO update(Long id, @Valid UsuarioRequestDTO dto) {
        Usuario entity = this.findUsuarioById(id);
        entity.update(dto, this.usuarioRepository);
        entity = this.usuarioRepository.save(entity);
        return new UsuarioResponseDTO(entity);
    }

    private Usuario findUsuarioById(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.USUARIO_NOT_FOUND));
    }

    private Usuario findUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessage.USUARIO_NOT_FOUND));
    }

    public void delete(Long id) {
        try {
            this.usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ExceptionMessage.USUARIO_NOT_FOUND);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUsuarioByUsername(username);
    }
    
    public Usuario authenticated(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return this.findUsuarioByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username");
        }
    }

    public UsuarioResponseDTO getMe(){
        Usuario usuario = this.authenticated();
        return new UsuarioResponseDTO(usuario);
    }
}
