package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandaPProdutoDto {

    private Long demandaPProdutoId;
    private Long tamanhoProdutoId;
    private String descricao;
    private String unidadeMedida;
    private BigDecimal qtdeDemandada;
    private BigDecimal custoUnitarioDemandado;
    private String tipoDemanda;

}
