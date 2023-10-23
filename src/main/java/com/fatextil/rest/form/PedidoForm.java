package com.fatextil.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PedidoForm {

    @NotNull(message = "Id do cliente não preenchido.")
    private Long clienteId;

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

    @NotNull(message = "O Valor do pedido não pode estar em branco.")
    @DecimalMax(value = "9999999999.99", message = "O valor deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal valor;

}
