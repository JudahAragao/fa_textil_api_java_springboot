package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class ItensPedidoForm {

    @NotNull(message = "Código do pedido não preenchido.")
    private Long codPedido;

    @NotNull(message = "Código do produto não preenchido.")
    private Long codProduto;

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "A quantidade não pode estar em branco.")
    private Integer qtde;

    @Size(max = 255)
    private String observacao;
}
