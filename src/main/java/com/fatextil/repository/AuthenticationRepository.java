package com.fatextil.repository;

import com.fatextil.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<UsuarioModel, Long>{
    UserDetails findByLogin(String login);
}
