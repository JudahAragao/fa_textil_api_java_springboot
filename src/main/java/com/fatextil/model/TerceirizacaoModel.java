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
    private Integer terceirizacaoId;

    @ManyToOne
    @JoinColumn(name = "terceirizadoId", referencedColumnName = "terceirizadoId")
    private TerceirizadoModel terceirizadoId;

    @Column(nullable = false)
    private LocalDate dataEnvio;

    @Column(nullable = false)
    private LocalTime horaEnvio;

    @Column
    private LocalDate dataFinalizacao;

    @Column
    private LocalTime horaFinalizacao;
}
