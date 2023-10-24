package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="TamanhoProduto")
public class TamanhoProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tamanhoProdutoId;

    @OneToOne
    @JoinColumn(name = "codProduto", referencedColumnName = "codProduto")
    private ProdutoModel codProduto;

    @Column(name = "tamanho", nullable = false, length = 4)
    private String tamanho;
}
