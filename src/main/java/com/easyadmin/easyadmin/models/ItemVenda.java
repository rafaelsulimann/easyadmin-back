package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.easyadmin.easyadmin.models.pks.ItemVendaPK;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.ITEM_VENDA)
@Data
@NoArgsConstructor
public class ItemVenda implements Serializable{

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemVendaPK id = new ItemVendaPK();

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal subTotal;

    public ItemVenda(Venda venda, Produto produto, Integer quantidade) {
        id.setVenda(venda);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.subTotal = produto.getPreco().multiply(new BigDecimal(quantidade));
    }

}
