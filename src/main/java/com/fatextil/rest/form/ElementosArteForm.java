package com.fatextil.rest.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private String fileName;

    private MultipartFile data;

}
