package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long usuarioId;
    private Long perfilAcessoId;
    private Long funcionarioId;
    private String login;
    private String senha;
    private Byte ativo;

}
