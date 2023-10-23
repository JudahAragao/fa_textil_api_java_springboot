package com.fatextil.repository;

import com.fatextil.model.ElementosArteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementosArteRepository extends JpaRepository<ElementosArteModel, Long>{

    // Exibir todos os dados
    List<ElementosArteModel> findAll();

    // Exibir dados apartir do ID
    Optional<ElementosArteModel> findById(Long id);

    Optional<ElementosArteModel> findByFilename(String filename);
}
