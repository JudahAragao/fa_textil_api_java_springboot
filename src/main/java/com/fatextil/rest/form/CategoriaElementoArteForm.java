package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class CategoriaElementoArteForm {
    @NotEmpty
    @NotBlank(message = "O nome da categoria não pode estar em branco.")
    @Size(max = 50)
    private String nomeCategoria;
}
