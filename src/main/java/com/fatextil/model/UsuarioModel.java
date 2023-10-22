package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "perfilAcessoId", referencedColumnName = "perfilAcessoId")
    private PerfilAcessoModel perfilAcessoId;

    @OneToOne
    @JoinColumn(name = "funcionarioId", referencedColumnName = "funcionarioId")
    private FuncionarioModel funcionarioId;

    @Column(nullable = false, length = 20)
    private String login;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false, columnDefinition = "bit")
    private Boolean ativo;
}
