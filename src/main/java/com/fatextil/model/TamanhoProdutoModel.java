package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="TamanhoProduto")
public class TamanhoProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tamanhoProdutoId;

    @OneToOne
    @JoinColumn(name = "codProduto", referencedColumnName = "codProduto")
    private ProdutoModel codProduto;

    @Column(nullable = false, length = 4)
    private String tamanho;
}
