package com.easyadmin.easyadmin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyadmin.easyadmin.models.Produto;
import com.easyadmin.easyadmin.utils.constraints.TableName;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    boolean existsByNome(String nome);

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.PRODUTO
            + " WHERE LOWER(nome) = LOWER(:nome) AND id != :id")
    Optional<Produto> findByNomeIgnoreCaseExcludesId(Long id, String nome);

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.PRODUTO + " WHERE LOWER(nome) = LOWER(:nome)")
    Optional<Produto> findByNomeIgnoreCase(String nome);

}
