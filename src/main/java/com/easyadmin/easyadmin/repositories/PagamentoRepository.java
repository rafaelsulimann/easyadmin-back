package com.easyadmin.easyadmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.easyadmin.easyadmin.models.Pagamento;
import com.easyadmin.easyadmin.utils.constraints.TableName;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, JpaSpecificationExecutor<Pagamento>{

    @Query(nativeQuery = true, value = "SELECT * FROM " + TableName.PAGAMENTO + " WHERE venda_id = :idVenda")
    Pagamento findPagamentoByVenda(Long idVenda);
    
}
