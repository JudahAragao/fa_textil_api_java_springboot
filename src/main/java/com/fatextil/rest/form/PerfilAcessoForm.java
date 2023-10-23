package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class PerfilAcessoForm {
    @NotEmpty
    @NotBlank(message = "O nome do perfil não pode estar em branco.")
    @Size(max = 10)
    private String nomePerfilAcesso;
}
