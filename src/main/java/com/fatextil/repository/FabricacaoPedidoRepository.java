package com.fatextil.repository;

import com.fatextil.model.FabricacaoPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FabricacaoPedidoRepository extends JpaRepository<FabricacaoPedidoModel, Long>{

    // Exibir todos os dados
    List<FabricacaoPedidoModel> findAll();

    // Exibir dados apartir do ID
    Optional<FabricacaoPedidoModel> findById(Long id);

}
