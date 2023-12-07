package com.fatextil.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name="Usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "perfilAcessoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private PerfilAcessoModel perfilAcessoId;

    @ManyToOne
    @JoinColumn(name = "funcionarioId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private FuncionarioModel funcionarioId;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Byte ativo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("VENDEDOR");
        roles.add("DESIGNER");

        String roleDefined = "ROLE_"+ roles.get((int) (perfilAcessoId.getPerfilAcessoId() - 1));

        return List.of(new SimpleGrantedAuthority(roleDefined));
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
}
