package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="FabricacaoPedido")
public class FabricacaoPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fabricacaoPedidoId;

    private Long etapasFabricacaoId;

    private Long itensPedidoId;

    private Long funcionarioId;

    private Long statusFabricacaoId;

    @Column(name = "dataInicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dataPrevisao", nullable = false)
    private LocalDate dataPrevisao;

    @Column(name = "dataFim")
    private LocalDate dataFim;
}
