package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TerceirizadoUpdateForm {

    @NotEmpty
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 120)
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

    @NotNull(message = "valor de atividade não pode ser nula.")
    private Boolean ativo;

}
