package com.fatextil.repository;

import com.fatextil.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long>{

    // Exibir todos os dados
    List<PedidoModel> findAll();

    // Exibir dados apartir do ID
    Optional<PedidoModel> findById(Long id);

}
