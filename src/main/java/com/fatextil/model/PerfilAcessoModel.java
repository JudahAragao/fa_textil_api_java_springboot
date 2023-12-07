package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="perfilacesso")
public class PerfilAcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perfilAcessoId;

    @Column(name = "nomePerfilAcesso", nullable = false, length = 20)
    private String nomePerfilAcesso;
}
