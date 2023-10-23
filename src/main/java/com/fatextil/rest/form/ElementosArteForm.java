package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ElementosArteForm {

    @NotNull(message = "Id de itens do pedido não preenchido.")
    private Long itensPedidoId;

    @NotNull(message = "Id do statuis do pedido pessoa física não preenchido.")
    private Long categoriaElementoId;

    @NotEmpty
    @NotBlank(message = "O Nome do arquivo não pode estar em branco.")
    @Size(max = 50)
    private String filename;

    @NotEmpty
    @NotBlank(message = "O caminho do arquivo não pode estar em branco.")
    @Size(max = 100)
    private String path;

}
