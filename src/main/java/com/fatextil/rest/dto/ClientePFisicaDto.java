package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePFisicaDto {

    private Long clientePFisicaId;
    private Long clienteId;
    private String nome;
    private String telefone;
    private String email;
    private String logradouro;
    private String numeroImovel;
    private String bairro;
    private String complemento;
    private String cep;
    private String cpf;
    private Boolean ativo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataCadastro;

}
