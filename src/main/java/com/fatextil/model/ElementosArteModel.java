package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="ElementosArte")
public class ElementosArteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementosArteId;

    @ManyToOne
    @JoinColumn(name = "itensPedidoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ItensPedidoModel itensPedidoId;

    @ManyToOne
    @JoinColumn(name = "categoriaElementoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private CategoriaElementoArteModel categoriaElementoId;

    @Column(name= "fileName", nullable = false, length = 50)
    private String fileName;

    @Column(name= "contentType", nullable = false)
    private String contentType;

    @Column(name= "data", nullable = false)
    @Lob
    private byte[] data;
}
