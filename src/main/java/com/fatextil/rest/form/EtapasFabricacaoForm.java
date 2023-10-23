package com.fatextil.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class EtapasFabricacaoForm {
    @NotEmpty
    @NotBlank(message = "O nome do status não pode estar em branco.")
    @Size(max = 10)
    private String nomeEtapa;
}
