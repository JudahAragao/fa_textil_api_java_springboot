package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class StatusFabricacaoForm {
    @NotEmpty
    @NotBlank(message = "O nome do status não pode estar em branco.")
    @Size(max = 10)
    private String nomeStatusFabricacao;
}
