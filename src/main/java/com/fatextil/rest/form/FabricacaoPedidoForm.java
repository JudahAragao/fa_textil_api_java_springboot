package com.fatextil.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
public class FabricacaoPedidoForm {

    private Long etapasFabricacaoId;

    private Long itensPedidoId;

    private Long funcionarioId;

    @NotNull(message = "Id do status de fabricação não preenchido.")
    private Long statusFabricacaoId;

    @NotNull(message = "Data de inicio não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @NotNull(message = "Data de previsão não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
}
