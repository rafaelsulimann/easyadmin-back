package com.easyadmin.easyadmin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.utils.constraints.TableName;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria>{

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.CATEGORIA + " WHERE LOWER(nome) = LOWER(:nome) AND id != :id")
    Optional<Categoria> findByNomeIgnoreCaseExcludesId(Long id, String nome);

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.CATEGORIA + " WHERE LOWER(nome) = LOWER(:nome)")
    Optional<Categoria> findByNomeIgnoreCase(String nome);
    
}
