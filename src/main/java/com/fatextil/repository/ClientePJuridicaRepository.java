package com.fatextil.repository;

import com.fatextil.model.ClientePJuridicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientePJuridicaRepository extends JpaRepository<ClientePJuridicaModel, Long>{

    List<ClientePJuridicaModel> findAll();
    Optional<ClientePJuridicaModel> findById(Long id);
    Optional<ClientePJuridicaModel> findByCnpj(String cnpj);
    Optional<ClientePJuridicaModel> findByRazaoSocial(String razaoSocial);
    Optional<ClientePJuridicaModel> findByTelefone(String telefone);
    Optional<ClientePJuridicaModel> findByEmail(String email);
    Optional<ClientePJuridicaModel> findByInscricaoEstadual(String email);
    Optional<ClientePJuridicaModel> findByInscricaoMunicipal(String inscricaoMunicipal);
}
