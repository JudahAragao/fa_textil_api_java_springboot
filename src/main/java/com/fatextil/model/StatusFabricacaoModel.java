package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="StatusFabricacao")
public class StatusFabricacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusFabricacaoId;

    @Column(nullable = false, length = 10)
    private String nomeStatusFabricacao;

}
