package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="ElementosArte")
public class ElementosArteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementosArteId;
    private Long itensPedidoId;
    private Long categoriaElementoId;

    @Column(name= "fileName", nullable = false, length = 50)
    private String fileName;

    @Column(name= "contentType", nullable = false)
    private String contentType;

    @Column(name= "data", nullable = false)
    @Lob
    private byte[] data;
}
