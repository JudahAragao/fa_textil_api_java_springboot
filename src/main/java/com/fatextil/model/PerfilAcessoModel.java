package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="PerfilAcesso")
public class PerfilAcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perfilAcessoId;

    @Column(nullable = false, length = 20)
    private String nomePerfilAcesso;
}
