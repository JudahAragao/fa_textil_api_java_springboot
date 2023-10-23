package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class ItensPedidoUpdateForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "A quantidade não pode estar em branco.")
    private Integer qtde;

    @NotEmpty
    @Size(max = 255)
    private String observacao;
}
