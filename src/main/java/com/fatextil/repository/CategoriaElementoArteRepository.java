package com.fatextil.repository;

import com.fatextil.model.CategoriaElementoArteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaElementoArteRepository extends JpaRepository<CategoriaElementoArteModel, Long>{

    // Exibir todos os dados
    List<CategoriaElementoArteModel> findAll();

    // Exibir dados apartir do ID
    Optional<CategoriaElementoArteModel> findById(Long id);

    Optional<CategoriaElementoArteModel> findByNomeCategoria(String nomeCategoria);
}
