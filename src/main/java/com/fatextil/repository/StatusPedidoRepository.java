package com.fatextil.repository;

import com.fatextil.model.StatusPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusPedidoRepository extends JpaRepository<StatusPedidoModel, Long>{

    // Exibir todos os dados
    List<StatusPedidoModel> findAll();

    // Exibir dados apartir do ID
    Optional<StatusPedidoModel> findById(Long id);

    Optional<StatusPedidoModel> findByNomeStatusPedido(String nomeStatusPedido);
}
