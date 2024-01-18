package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TamanhoProdutoDto {

    private Long id;
    private Long codProduto;
    private String tamanho;

}
