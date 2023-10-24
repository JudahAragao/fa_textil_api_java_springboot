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

    @ManyToOne
    @JoinColumn(name = "etapasFabricacaoId", referencedColumnName = "etapasFabricacaoId")
    private EtapasFabricacaoModel etapasFabricacaoId;

    @ManyToOne
    @JoinColumn(name = "itensPedidoId", referencedColumnName = "itensPedidoId")
    private ItensPedidoModel itensPedidoId;

    @ManyToOne
    @JoinColumn(name = "funcionarioId", referencedColumnName = "funcionarioId")
    private FuncionarioModel funcionarioId;

    @ManyToOne
    @JoinColumn(name = "statusFabricacaoId", referencedColumnName = "statusFabricacaoId")
    private StatusFabricacaoModel statusFabricacaoId;

    @Column(name = "dataInicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dataPrevisao", nullable = false)
    private LocalDate dataPrevisao;

    @Column(name = "dataFim")
    private LocalDate dataFim;
}
