package com.fatextil.repository;

import com.fatextil.model.StatusFabricacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusFabricacaoRepository extends JpaRepository<StatusFabricacaoModel, Long>{

    // Exibir todos os dados
    List<StatusFabricacaoModel> findAll();

    // Exibir dados apartir do ID
    Optional<StatusFabricacaoModel> findById(Long id);

    Optional<StatusFabricacaoModel> findByNomeStatusFabricacao(String nomeStatusFabricacao);
}
