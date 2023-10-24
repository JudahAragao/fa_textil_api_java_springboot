package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="CategoriaElementoArte")
public class CategoriaElementoArteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaElementoId;

    @Column(name= "nomeCategoria", nullable = false, length = 50)
    private String nomeCategoria;
}
