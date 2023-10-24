package com.fatextil.model;

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
    @JoinColumn(name = "codPedido", referencedColumnName = "codPedido")
    private PedidoModel codPedido;

    @ManyToOne
    @JoinColumn(name = "codProduto", referencedColumnName = "codProduto")
    private ProdutoModel codProduto;

    @Column(name = "descricao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @Column(name = "qtde", nullable = false)
    private Integer qtde;

    @Column(name = "observacao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String observacao;
}
