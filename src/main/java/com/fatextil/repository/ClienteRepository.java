package com.fatextil.repository;

import com.fatextil.model.ClienteModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

    Optional<ClienteModel> findByTipoCliente(String tipoCliente);

}
