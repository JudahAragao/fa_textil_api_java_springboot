package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementosArteDto {

    private Long elementosArteId;
    private Long itensPedidoId;
    private Long categoriaElementoId;
    private String filename;
    private String path;

}
