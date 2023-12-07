package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioUpdateForm {

    @NotNull(message = "Id do perfil de acesso não preenchido.")
    private Long perfilAcessoId;

    @NotEmpty
    @NotBlank(message = "O Login não pode estar em branco.")
    @Size(max = 20)
    private String login;

    @NotEmpty
    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(max = 100)
    private String senha;

    @NotNull(message = "Valor de atividade não pode ser nula.")
    private Byte ativo;
}
