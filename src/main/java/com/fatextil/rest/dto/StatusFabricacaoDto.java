package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusFabricacaoDto {

    private Long statusFabricacaoId;
    private String nomeStatusFabricacao;

}
