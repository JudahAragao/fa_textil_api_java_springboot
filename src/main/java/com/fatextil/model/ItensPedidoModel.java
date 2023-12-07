package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="ItensPedido")
public class ItensPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itensPedidoId;

    @ManyToOne
    @JoinColumn(name = "codPedido", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private PedidoModel codPedido;

    @ManyToOne
    @JoinColumn(name = "codProduto", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ProdutoModel codProduto;

    @Column(name = "descricao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(name = "qtde", nullable = false)
    private Integer qtde;

    @Column(name = "observacao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String observacao;
}
