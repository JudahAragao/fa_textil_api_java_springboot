package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class TamanhoProdutoForm {

    @NotNull(message = "Código do produto não preenchido.")
    private Long codProduto;

    @NotEmpty
    @NotBlank(message = "O tamanho não pode estar em branco.")
    @Size(max = 4)
    private String tamanho;

}
