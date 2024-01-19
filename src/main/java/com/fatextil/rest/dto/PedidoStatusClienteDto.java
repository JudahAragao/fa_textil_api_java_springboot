package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoStatusClienteDto {

    private Long codPedido;
    private String descricao;
    private String status;
    private String nomeClienteFisico;
    private String nomeClienteJuridico;

}
