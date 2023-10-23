package com.fatextil.repository;

import com.fatextil.model.PerfilAcessoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilAcessoRepository extends JpaRepository<PerfilAcessoModel, Long>{

    // Exibir todos os dados
    List<PerfilAcessoModel> findAll();

    // Exibir dados apartir do ID
    Optional<PerfilAcessoModel> findById(Long id);

    Optional<PerfilAcessoModel> findByNomePerfilAcesso(String nomePerfilAcesso);
}
