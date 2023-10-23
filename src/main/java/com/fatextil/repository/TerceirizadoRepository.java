package com.fatextil.repository;

import com.fatextil.model.TerceirizadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerceirizadoRepository extends JpaRepository<TerceirizadoModel, Long>{

    // Exibir todos os dados
    List<TerceirizadoModel> findAll();

    // Exibir dados apartir do ID
    Optional<TerceirizadoModel> findById(Long id);

}
