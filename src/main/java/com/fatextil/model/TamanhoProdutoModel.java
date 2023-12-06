package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="TamanhoProduto")
public class TamanhoProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tamanhoProdutoId;

    @ManyToOne
    @JoinColumn(name = "codProduto", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ProdutoModel codProduto;

    @Column(name = "tamanho", nullable = false, length = 4)
    private String tamanho;

}
