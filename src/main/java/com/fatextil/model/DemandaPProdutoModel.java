package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToOne
    @JoinColumn(name = "tamanhoProdutoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
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
