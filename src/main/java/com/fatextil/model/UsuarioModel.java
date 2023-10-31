package com.fatextil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name="Usuario")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    private Long perfilAcessoId;

    private Long funcionarioId;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Transient
    private String nomePerfilAcesso;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(List.of(new SimpleGrantedAuthority("ROLE_" + nomePerfilAcesso)));
        return List.of(new SimpleGrantedAuthority("ROLE_" + nomePerfilAcesso));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Adicione um método para definir o nomePerfilAcesso
    public void setNomePerfilAcesso(String nomePerfilAcesso) {
        this.nomePerfilAcesso = nomePerfilAcesso;
    }
}
