package com.fatextil.repository;

import com.fatextil.model.ItensPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Long>{

    // Exibir todos os dados
    List<ItensPedidoModel> findAll();

    // Exibir dados apartir do ID
    Optional<ItensPedidoModel> findById(Long id);

}
