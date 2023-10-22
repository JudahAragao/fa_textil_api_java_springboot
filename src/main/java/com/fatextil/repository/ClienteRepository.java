package com.fatextil.repository;

import com.fatextil.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

    // Exibir todos os dados
    List<ClienteModel> findAll();

    // Exibir dados apartir do ID
    Optional<ClienteModel> findById(Long id);

}
