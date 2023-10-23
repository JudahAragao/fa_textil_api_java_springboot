package com.fatextil.repository;

import com.fatextil.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

    // Exibir todos os dados
    List<ProdutoModel> findAll();

    // Exibir dados apartir do ID
    Optional<ProdutoModel> findById(Long id);

}
