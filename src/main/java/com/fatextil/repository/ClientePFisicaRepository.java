package com.fatextil.repository;

import com.fatextil.model.ClienteModel;
import com.fatextil.model.ClientePFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClientePFisicaRepository extends JpaRepository<ClientePFisicaModel, Long>{

    Optional<ClientePFisicaModel> findByNome(String nome);
    Optional<ClientePFisicaModel> findByClienteId(ClienteModel clienteId);
    Optional<ClientePFisicaModel> findByTelefone(String telefone);
    Optional<ClientePFisicaModel> findByEmail(String email);
    Optional<ClientePFisicaModel> findByLogradouro(String logradouro);
    Optional<ClientePFisicaModel> findByNumeroImovel(String numeroImovel);
    Optional<ClientePFisicaModel> findByBairro(String bairro);
    Optional<ClientePFisicaModel> findByComplemento(String complemento);
    Optional<ClientePFisicaModel> findByCep(String cep);
    Optional<ClientePFisicaModel> findByCpf(String cpf);
//    Optional<ClientePFisicaModel> findByAtivo(Byte ativo);
    Optional<ClientePFisicaModel> findByDataCadastro(LocalDate dataCadastro);
}
