package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="Funcionario")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funcionarioId;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "logradouro", nullable = false, length = 60)
    private String logradouro;

    @Column(name = "numeroImovel", nullable = false, length = 10)
    private String numeroImovel;

    @Column(name = "bairro", nullable = false, length = 20)
    private String bairro;

    @Column(name = "complemento", length = 50)
    private String complemento;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "ativo", nullable = false, columnDefinition = "bit")
    private Byte ativo;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDate dataCadastro;
}
