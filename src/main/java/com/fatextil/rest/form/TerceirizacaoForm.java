package com.fatextil.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TerceirizacaoForm {

    @NotNull(message = "Id da empresa terceirizada não preenchido.")
    private Long terceirizadoId;

    @NotNull(message = "Id da fabricacao não preenchido.")
    private Long fabricacaoPedidoId;

    @NotNull(message = "Data de envio não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEnvio;

    @NotNull(message = "Hora de envio não pode ser nula.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEnvio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinalizacao;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFinalizacao;

}
