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

    @Column(name= "descricao", nullable = false, length = 60)
    private String descricao;

    @Column(name= "unidadeMedida", nullable = false, length = 2)
    private String unidadeMedida;

    @Column(name= "qtdeDemandada", nullable = false, precision = 10, scale = 2)
    private BigDecimal qtdeDemandada;

    @Column(name= "custoUnitarioDemanda", nullable = false, precision = 10, scale = 2)
    private BigDecimal custoUnitarioDemanda;
}
