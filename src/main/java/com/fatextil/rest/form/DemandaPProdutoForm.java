package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class DemandaPProdutoForm {

    @NotNull(message = "Id do tamanho do produto não preenchido.")
    private Long tamanhoProdutoId;

    @NotEmpty
    @NotBlank(message = "A descricao não pode estar em branco.")
    @Size(max = 60)
    private String descricaoDemanda;

    @NotEmpty
    @NotBlank(message = "A unidade de medida não pode estar em branco.")
    @Size(max = 2)
    private String unidadeMedida;

    @NotNull(message = "A quantidade não pode estar em branco.")
    @DecimalMax(value = "9999999999.99", message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal qtdeDemandada;

    @NotNull(message = "O custo unitário demandado não pode estar em branco.")
    @DecimalMax(value = "9999999999.99", message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal custoUnitarioDemandado;

}
