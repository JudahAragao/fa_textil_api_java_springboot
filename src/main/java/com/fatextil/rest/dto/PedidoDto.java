package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    private Long pedidoId;
    private Long clienteId;
    private Long statusPedidoId;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPedido;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaPedido;
    private BigDecimal valor;
}
