package com.fatextil.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PedidoForm {

    private Long clientePJuridicaId;

    private Long clientePFisicaId;

    @NotNull(message = "Id do statuis do pedido pessoa física não preenchido.")
    private Long statusPedidoId;

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "Data do pedido não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPedido;

    @NotNull(message = "Hora do pedido não pode ser nula.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaPedido;

}
