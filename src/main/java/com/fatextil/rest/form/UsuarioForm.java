package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UsuarioForm {

    @NotNull(message = "Id do perfil de acesso não preenchido.")
    private Long perfilAcessoId;

    @NotNull(message = "Id do funcionario não preenchido.")
    private Long funcionarioId;

    @NotEmpty
    @NotBlank(message = "O Login não pode estar em branco.")
    @Size(max = 20)
    private String login;

    @NotEmpty
    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(max = 100)
    private String senha;

    @NotNull(message = "Valor de atividade não pode ser nula.")
    private Boolean ativo;
}
