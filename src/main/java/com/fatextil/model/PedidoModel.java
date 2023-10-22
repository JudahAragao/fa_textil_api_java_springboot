package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name="Pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codPedido;

    @ManyToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "clienteId")
    private ClienteModel clienteId;

    @ManyToOne
    @JoinColumn(name = "statusPedidoId", referencedColumnName = "statusPedidoId")
    private StatusPedidoModel statusPedidoId;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private LocalTime horaPedido;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal valorPedido;
}
