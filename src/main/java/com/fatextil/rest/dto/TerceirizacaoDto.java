package com.fatextil.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerceirizacaoDto {

    private Long id;
    private Long fabricacaoPedidoId;
    private Long terceirizadoId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEnvio;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEnvio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinalizacao;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFinalizacao;

}
