package com.easyadmin.easyadmin.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.easyadmin.easyadmin.dtos.UsuarioRequestDTO;
import com.easyadmin.easyadmin.repositories.UsuarioRepository;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.utils.constraints.ExceptionMessage;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.USUARIO)
@NoArgsConstructor
@Data
public class Usuario implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = TableName.USUARIOS_ROLES, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Venda> vendas = new ArrayList<>();

    public Usuario(@Valid UsuarioRequestDTO dto, UsuarioRepository usuarioRepository) {
        this.username = this.validateDuplicidadeUsername(dto, usuarioRepository);
        this.password = dto.getPassword();
    }

    public void update(@Valid UsuarioRequestDTO dto, UsuarioRepository usuarioRepository) {
        this.username = this.validateDuplicidadeUsernameExcludesId(dto, usuarioRepository);
        this.password = dto.getPassword();
    }

    public boolean hasRole(String roleName){
        for(Role role : roles){
            if(role.getAuthority().equals(roleName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private String validateDuplicidadeUsername(@Valid UsuarioRequestDTO dto, UsuarioRepository usuarioRepository) {
        Optional<Usuario> usuario = usuarioRepository.findByUsernameIgnoreCase(dto.getUsername());
        if(usuario.isPresent()){
            throw new DatabaseException(ExceptionMessage.EXISTS_USUARIO_BY_USERNAME);
        } 
        return dto.getUsername();
    }

    private String validateDuplicidadeUsernameExcludesId(@Valid UsuarioRequestDTO dto, UsuarioRepository usuarioRepository) {
        Optional<Usuario> usuario = usuarioRepository.findByUsernameIgnoreCase(dto.getUsername());
        if(usuario.isPresent()){
            if(usuario.get().getId() != id){
                throw new DatabaseException(ExceptionMessage.EXISTS_USUARIO_BY_USERNAME);
            }
        } 
        return dto.getUsername();
    }

}
