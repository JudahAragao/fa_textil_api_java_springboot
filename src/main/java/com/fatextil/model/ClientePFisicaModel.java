package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="ClientePFisica")
public class ClientePFisicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientePFisicaId;

    @OneToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "clienteId")
    private ClienteModel clienteId;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 60)
    private String logradouro;

    @Column(nullable = false, length = 10)
    private String numeroImovel;

    @Column(nullable = false, length = 20)
    private String bairro;

    @Column(length = 50)
    private String complemento;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, columnDefinition = "bit")
    private Boolean ativo;

    @Column(nullable = false)
    private LocalDate dataCadastro;
}
