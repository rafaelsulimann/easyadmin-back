package com.easyadmin.easyadmin.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.easyadmin.easyadmin.dtos.VendaRequestDTO;
import com.easyadmin.easyadmin.models.enums.TipoPagamento;
import com.easyadmin.easyadmin.utils.constraints.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.PAGAMENTO)
@Data
@NoArgsConstructor
public class Pagamento implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime momento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @OneToOne
    @MapsId
    private Venda venda;

    public Pagamento(Venda venda, @Valid VendaRequestDTO dto) {
        this.momento = LocalDateTime.now(ZoneId.of("UTC"));
        this.tipoPagamento = dto.getTipoPagamento();
        this.venda = venda;
    }

}
