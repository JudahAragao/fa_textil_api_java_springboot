package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="EtapasFabricacao")
public class EtapasFabricacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etapasFabricacaoId;

    @Column(nullable = false, length = 100)
    private String nomeEtapa;
}
