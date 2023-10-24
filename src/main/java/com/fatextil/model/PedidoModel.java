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
    private Long codPedido;

    private Long clienteId;

    private Long statusPedidoId;

    @Column(name = "descricao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(name = "dataPedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "horaPedido", nullable = false)
    private LocalTime horaPedido;

    @Column(name = "valorPedido", nullable = false, precision = 20, scale = 2)
    private BigDecimal valorPedido;
}
