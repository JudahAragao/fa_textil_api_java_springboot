package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDto {

    private Long id;
    private Long codPedido;
    private Long codProduto;
    private String descricao;
    private Integer qtde;
    private String observacao;

}
