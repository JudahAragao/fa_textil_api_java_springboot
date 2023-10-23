package com.fatextil.repository;

import com.fatextil.model.DemandaPProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandaPProdutoRepository extends JpaRepository<DemandaPProdutoModel, Long>{

    // Exibir todos os dados
    List<DemandaPProdutoModel> findAll();

    // Exibir dados apartir do ID
    Optional<DemandaPProdutoModel> findById(Long id);


}
