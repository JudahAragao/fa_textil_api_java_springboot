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
@Table(name="Terceirizacao")
public class TerceirizacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long terceirizacaoId;

    @ManyToOne
    @JoinColumn(name = "terceirizadoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private TerceirizadoModel terceirizadoId;

    @ManyToOne
    @JoinColumn(name = "fabricacaoPedidoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private FabricacaoPedidoModel fabricacaoPedidoId;

    @Column(name = "dataEnvio", nullable = false)
    private LocalDate dataEnvio;

    @Column(name = "horaEnvio", nullable = false)
    private LocalTime horaEnvio;

    @Column(name = "dataFinalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "horaFinalizacao")
    private LocalTime horaFinalizacao;
}
