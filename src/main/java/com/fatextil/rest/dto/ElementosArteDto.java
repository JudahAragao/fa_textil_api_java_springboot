package com.fatextil.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementosArteDto {

    private Long id;
    private Long itensPedidoId;
    private Long categoriaElementoId;
    private String contentType;
    private String fileName;
    private byte[] data;

}
