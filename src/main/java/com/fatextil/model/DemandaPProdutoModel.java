package com.fatextil.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="DemandaPProduto")
public class DemandaPProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandaPProdutoId;

    @OneToOne
    @JoinColumn(name = "tamanhoProdutoId", referencedColumnName = "tamanhoProdutoId")
    private TamanhoProdutoModel tamanhoProdutoId;

    @Column(nullable = false, length = 60)
    private String descricao;

    @Column(nullable = false, length = 2)
    private String unidadeMedida;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal qtdeDemandada;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal custoUnitarioDemanda;
}
