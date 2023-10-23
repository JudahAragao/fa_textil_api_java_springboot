package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerceirizadoDto {

    private Long terceirizadoId;
    private String nome;
    private String telefone;
    private String email;
    private String logradouro;
    private String numeroImovel;
    private String bairro;
    private String complemento;
    private String cep;
    private String cnpj;
    private Boolean ativo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

}
