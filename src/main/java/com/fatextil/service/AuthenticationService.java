package com.fatextil.service;

import com.fatextil.model.PerfilAcessoModel;
import com.fatextil.model.UsuarioModel;
import com.fatextil.repository.AuthenticationRepository;
import com.fatextil.repository.PerfilAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    public void carregarNomePerfilAcesso(UsuarioModel usuario) {
        Long perfilAcessoId = usuario.getPerfilAcessoId();
        PerfilAcessoModel perfilAcesso = perfilAcessoRepository.findById(perfilAcessoId).orElse(null);

        if (perfilAcesso != null) {
            usuario.setNomePerfilAcesso(perfilAcesso.getNomePerfilAcesso());
        } else {
            // Lide com a situação em que o perfil de acesso não existe
            usuario.setNomePerfilAcesso("DEFAULT"); // Substitua "ROLE_DEFAULT" pela lógica apropriada
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationRepository.findByLogin(username);
    }
}
