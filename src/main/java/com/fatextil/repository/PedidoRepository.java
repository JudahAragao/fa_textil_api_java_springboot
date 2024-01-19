package com.fatextil.repository;

import com.fatextil.model.PedidoModel;
import com.fatextil.rest.dto.PedidoStatusClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long>{

    // Exibir todos os dados
    List<PedidoModel> findAll();

    // Exibir dados apartir do ID
    Optional<PedidoModel> findById(Long id);

    @Query("SELECT new com.fatextil.rest.dto.PedidoStatusClienteDto(p.codPedido, p.descricao, sp.nomeStatusPedido, cpf.nome, cpj.razaoSocial) " +
            "FROM PedidoModel p " +
            "JOIN p.statusPedidoId sp " +
            "LEFT JOIN ClientePFisicaModel cpf ON p.clientePFisicaId = cpf.clientePFisicaId " +
            "LEFT JOIN ClientePJuridicaModel cpj ON p.clientePJuridicaId = cpj.clientePJuridicaId")
    List<PedidoStatusClienteDto> findAllPedidosStatusTamanho();

}
