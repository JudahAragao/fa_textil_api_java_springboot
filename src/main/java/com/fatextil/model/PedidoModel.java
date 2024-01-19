package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name="Pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPedido;

    @Column(name = "clientePJuridicaId")
    private Long clientePJuridicaId;

    @Column(name = "clientePFisicaId")
    private Long clientePFisicaId;

    @ManyToOne
    @JoinColumn(name = "statusPedidoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private StatusPedidoModel statusPedidoId;

    @Column(name = "descricao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(name = "dataPedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "horaPedido", nullable = false)
    private LocalTime horaPedido;

}
