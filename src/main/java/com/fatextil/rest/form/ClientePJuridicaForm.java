package com.fatextil.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
public class ClientePJuridicaForm {

    @NotNull(message = "Id do cliente pessoa física não preenchido.")
    private Long clienteId;

    @NotEmpty
    @NotBlank(message = "A razão social não pode estar em branco.")
    @Size(max = 100)
    private String razaoSocial;

    @NotEmpty
    @NotBlank(message = "A razão social não pode estar em branco.")
    @Size(max = 80)
    private String representante;

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
    @Size(max = 100)
    private String site;

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

    @Size(max = 50)
    private String complemento;

    @NotEmpty
    @NotBlank(message = "O CEP não pode estar em branco.")
    @Size(max = 9)
    private String cep;

    @Size(max = 14)
    private String inscricaoEstatual;

    @Size(max = 14)
    private String inscricaoMunicipal;

    @NotEmpty
    @NotBlank
    @CNPJ(message = "O Número do CNPJ informado é inválido.")
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotNull(message = "valor de atividade não pode ser nula.")
    private Byte ativo;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
}
