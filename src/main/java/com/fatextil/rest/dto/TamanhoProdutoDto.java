package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TamanhoProdutoDto {

    private Long tamanhoProdutoId;
    private Long codProduto;
    private String tamanho;

}
