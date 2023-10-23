package com.fatextil.repository;

import com.fatextil.model.TamanhoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TamanhoProdutoRepository extends JpaRepository<TamanhoProdutoModel, Long>{

    // Exibir todos os dados
    List<TamanhoProdutoModel> findAll();

    // Exibir dados apartir do ID
    Optional<TamanhoProdutoModel> findById(Long id);

}
