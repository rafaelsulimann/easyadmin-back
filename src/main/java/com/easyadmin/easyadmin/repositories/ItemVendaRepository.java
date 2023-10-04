package com.easyadmin.easyadmin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.easyadmin.easyadmin.models.ItemVenda;
import com.easyadmin.easyadmin.models.pks.ItemVendaPK;
import com.easyadmin.easyadmin.utils.constraints.TableName;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, ItemVendaPK>, JpaSpecificationExecutor<ItemVenda>{

    @Query(nativeQuery = true, value = "SELECT * "
    + "FROM " + TableName.ITEM_VENDA + " "
    + "INNER JOIN " + TableName.VENDA + " ON " + TableName.VENDA + ".ID = " + TableName.ITEM_VENDA + ".VENDA_ID "
    + "WHERE " + TableName.ITEM_VENDA + ".VENDA_ID = :idVenda")
    List<ItemVenda> findAllItemVendaByVenda(Long idVenda);
    
}
