package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePJuridicaDto {

    private Long id;
    private Long clienteId;
    private String razaoSocial;
    private String representante;
    private String telefone;
    private String email;
    private String site;
    private String logradouro;
    private String numeroImovel;
    private String bairro;
    private String complemento;
    private String cep;
    private String inscricaoEstatual;
    private String inscricaoMunicipal;
    private String cnpj;
    private Byte ativo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

}
