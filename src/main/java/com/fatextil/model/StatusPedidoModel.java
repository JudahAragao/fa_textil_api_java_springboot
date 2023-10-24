package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="StatusPedido")
public class StatusPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusPedidoId;

    @Column(name = "nomeStatusPedido", nullable = false, length = 100)
    private String nomeStatusPedido;
}
