package com.fatextil.repository;

import com.fatextil.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long>{

    // Exibir todos os dados
    List<FuncionarioModel> findAll();

    // Exibir dados apartir do ID
    Optional<FuncionarioModel> findById(Long id);

    Optional<FuncionarioModel> findByNome(String nome);

    Optional<FuncionarioModel> findByTelefone(String telefone);

    Optional<FuncionarioModel> findByEmail(String email);

    Optional<FuncionarioModel> findByCpf(String cpf);
}
