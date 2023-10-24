package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "perfilAcessoId", referencedColumnName = "perfilAcessoId")
    private PerfilAcessoModel perfilAcessoId;

    @OneToOne
    @JoinColumn(name = "funcionarioId", referencedColumnName = "funcionarioId")
    private FuncionarioModel funcionarioId;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
