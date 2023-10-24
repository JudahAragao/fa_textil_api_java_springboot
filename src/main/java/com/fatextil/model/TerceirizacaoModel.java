package com.fatextil.model;

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
    @JoinColumn(name = "terceirizadoId", referencedColumnName = "terceirizadoId")
    private TerceirizadoModel terceirizadoId;

    @ManyToOne
    @JoinColumn(name = "fabricacaoPedidoId", referencedColumnName = "fabricacaoPedidoId")
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
