package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
public class ProdutoForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 120)
    private String descricaoProduto;

    @NotNull(message = "O Valor do produto não pode estar em branco.")
    @DecimalMax(value = "9999999999.99", message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal valorProduto;

}
