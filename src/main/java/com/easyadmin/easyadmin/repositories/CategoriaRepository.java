package com.easyadmin.easyadmin.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.utils.constraints.TableName;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.CATEGORIA
            + " WHERE LOWER(nome) = LOWER(:nome) AND id != :id")
    Optional<Categoria> findByNomeIgnoreCaseExcludesId(Long id, String nome);

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.CATEGORIA + " WHERE LOWER(nome) = LOWER(:nome)")
    Optional<Categoria> findByNomeIgnoreCase(String nome);

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.CATEGORIA + " as categoria INNER JOIN "
            + TableName.PRODUTO_CATEGORIA
            + " as produto ON produto.categoria_id = categoria.ID WHERE produto.produto_id = :id")
    List<Categoria> findAllCategoriasByProduto(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM " + TableName.PRODUTO_CATEGORIA + " WHERE produto_id = :produtoId")
    void deleteCategoriasProduto(Long produtoId);

}
