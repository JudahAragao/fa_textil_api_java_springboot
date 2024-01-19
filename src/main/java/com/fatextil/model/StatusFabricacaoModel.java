package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="StatusFabricacao")
public class StatusFabricacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusFabricacaoId;

    @Column(name = "nomeStatusFabricacao", nullable = false, length = 100)
    private String nomeStatusFabricacao;

}
