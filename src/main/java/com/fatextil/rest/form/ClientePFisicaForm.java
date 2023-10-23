package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;


@Data
public class ClientePFisicaForm {

    @NotNull(message = "Id do cliente pessoa física não preenchido.")
    private Long clienteId;

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 100)
    private String nome;

    @NotEmpty
    @NotBlank(message = "O Telefone não pode estar em branco.")
    @Size(max = 14)
    private String telefone;

    @NotEmpty
    @NotBlank
    @Email(message = "O Endereço de e-mail é inválido.")
    @Size(max = 60)
    private String email;

    @NotEmpty
    @NotBlank(message = "O logradouro não pode estar em branco.")
    @Size(max = 60)
    private String logradouro;

    @NotEmpty
    @NotBlank(message = "O número do imóvel não pode estar em branco.")
    @Size(max = 10)
    private String numeroImovel;

    @NotEmpty
    @NotBlank(message = "O bairro não pode estar em branco.")
    @Size(max = 20)
    private String bairro;

    @NotEmpty
    @NotBlank(message = "O complemento não pode estar em branco.")
    @Size(max = 50)
    private String complemento;

    @NotEmpty
    @NotBlank(message = "O CEP não pode estar em branco.")
    @Size(max = 9)
    private String cep;

    @NotEmpty
    @NotBlank
    @CPF(message = "O Número do CPF informado é inválido.")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull(message = "valor de atividade não pode ser nula.")
    private Boolean ativo;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
}
