package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JoinColumn(name = "etapasFabricacaoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private EtapasFabricacaoModel etapasFabricacaoId;

    @ManyToOne
    @JoinColumn(name = "itensPedidoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ItensPedidoModel itensPedidoId;

    @ManyToOne
    @JoinColumn(name = "funcionarioId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private FuncionarioModel funcionarioId;

    @ManyToOne
    @JoinColumn(name = "statusFabricacaoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private StatusFabricacaoModel statusFabricacaoId;

    @Column(name = "dataInicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dataPrevisao", nullable = false)
    private LocalDate dataPrevisao;

    @Column(name = "dataFim")
    private LocalDate dataFim;
}
