package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricacaoPedidoDto {

    private Long id;
    private Long etapasFabricacaoId;
    private Long itensPedidoId;
    private Long funcionarioId;
    private Long statusFabricacaoId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

}
