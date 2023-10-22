package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="Produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codProduto;

    @Column(nullable = false, length = 120)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorProduto;
}
