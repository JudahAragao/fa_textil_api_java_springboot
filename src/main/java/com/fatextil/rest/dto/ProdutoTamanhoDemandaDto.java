package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoTamanhoDemandaDto {

    private Long codProduto;
    private String descricao;
    private String tamanho;
    private BigDecimal valorProduto;

}
