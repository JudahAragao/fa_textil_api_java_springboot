package com.fatextil.repository;

import com.fatextil.model.ProdutoModel;
import com.fatextil.rest.dto.ProdutoTamanhoDemandaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

    // Exibir todos os dados
    List<ProdutoModel> findAll();

    // Exibir dados apartir do ID
    Optional<ProdutoModel> findById(Long id);


    @Query("SELECT new com.fatextil.rest.dto.ProdutoTamanhoDemandaDto(p.id, p.descricao, tp.tamanho, p.valorProduto) " +
            "FROM ProdutoModel p " +
            "INNER JOIN TamanhoProdutoModel tp ON p.id = tp.codProduto.id")
    List<ProdutoTamanhoDemandaDto> findAllProdutosWithTamanho();
}
