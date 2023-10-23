package com.fatextil.repository;

import com.fatextil.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    // Exibir todos os dados
    List<UsuarioModel> findAll();

    // Exibir dados apartir do ID
    Optional<UsuarioModel> findById(Long id);

    Optional<UsuarioModel> findByLogin(String login);
}
