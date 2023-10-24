package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="ClientePJuridica")
public class ClientePJuridicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientePJuridicaId;

    @OneToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "clienteId")
    private ClienteModel clienteId;

    @Column(name= "razaoSocial", nullable = false, length = 100)
    private String razaoSocial;

    @Column(name= "representante", nullable = false, length = 80)
    private String representante;

    @Column(name= "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name= "email", nullable = false, length = 60)
    private String email;

    @Column(name= "site", nullable = true, length = 100)
    private String site;

    @Column(name= "logradouro", nullable = false, length = 60)
    private String logradouro;

    @Column(name= "numeroImovel", nullable = false, length = 10)
    private String numeroImovel;

    @Column(name= "bairro", nullable = false, length = 20)
    private String bairro;

    @Column(name= "complemento", length = 50)
    private String complemento;

    @Column(name= "cep", nullable = false, length = 9)
    private String cep;

    @Column(name= "inscricaoEstadual", length = 14)
    private String inscricaoEstadual;

    @Column(name= "inscricaoMunicipal", length = 14)
    private String inscricaoMunicipal;

    @Column(name= "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name= "ativo", nullable = false)
    private Boolean ativo;

    @Column(name= "dataCadastro", nullable = false)
    private LocalDate dataCadastro;
}
