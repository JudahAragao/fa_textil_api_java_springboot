package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClienteForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 8)
    private String tipoCliente;

}
