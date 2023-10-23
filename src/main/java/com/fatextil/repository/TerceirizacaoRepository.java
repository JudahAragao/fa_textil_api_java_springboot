package com.fatextil.repository;

import com.fatextil.model.TerceirizacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerceirizacaoRepository extends JpaRepository<TerceirizacaoModel, Long>{

    // Exibir todos os dados
    List<TerceirizacaoModel> findAll();

    // Exibir dados apartir do ID
    Optional<TerceirizacaoModel> findById(Long id);

}
