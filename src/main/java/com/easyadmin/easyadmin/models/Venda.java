package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.easyadmin.easyadmin.dtos.VendaRequestDTO;
import com.easyadmin.easyadmin.services.ProdutoService;
import com.easyadmin.easyadmin.services.UsuarioService;
import com.easyadmin.easyadmin.services.exceptions.DatabaseException;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.VENDA)
@NoArgsConstructor
@Data
public class Venda implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime momento;

    @OneToMany(mappedBy = "id.venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(mappedBy = "venda", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @Column(nullable = false)
    private BigDecimal total;

    public Venda(@Valid VendaRequestDTO dto, UsuarioService usuarioService, ProdutoService produtoService) {
        this.momento = LocalDateTime.now(ZoneId.of("UTC"));
        this.itens = this.getItensVenda(dto, produtoService);
        this.usuario = usuarioService.authenticated();
        this.pagamento = new Pagamento(this, dto);
        this.total = this.getTotalVenda();
    }

    private BigDecimal getTotalVenda() {
        BigDecimal total = new BigDecimal(0);
        for(ItemVenda item : this.itens){
            total = total.add(item.getSubTotal());
        }
        return total;
    }

    private List<ItemVenda> getItensVenda(@Valid VendaRequestDTO dto, ProdutoService produtoService) {
        this.validateDuplicidadeItens(dto);
        dto.getItens().stream().forEach(itemDto -> {
            Produto produto = produtoService.findProdutoById(itemDto.getProdutoId());
            this.itens.add(new ItemVenda(this, produto, itemDto.getQuantidade()));
        });
        return this.itens;
    }

    private void validateDuplicidadeItens(@Valid VendaRequestDTO dto) {
        Set<Long> itensUnicos = dto.getItens().stream().map(itemDTO -> itemDTO.getProdutoId()).collect(Collectors.toSet());
        if (itensUnicos.size() != dto.getItens().size()) {
            throw new DatabaseException("Existem categorias duplicadas na lista enviada");
        }
    }

}
