package com.fatextil.repository;

import com.fatextil.model.EtapasFabricacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtapasFabricacaoRepository extends JpaRepository<EtapasFabricacaoModel, Long>{

    // Exibir todos os dados
    List<EtapasFabricacaoModel> findAll();

    // Exibir dados apartir do ID
    Optional<EtapasFabricacaoModel> findById(Long id);

    Optional<EtapasFabricacaoModel> findByNomeEtapa(String nomeEtapa);
}
