package com.fatextil.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/{token}")
    public ResponseEntity<?> validateToken(@PathVariable String token, Authentication authentication) {
        // Pode continuar mesmo que não esteja autenticado
        Collection<? extends GrantedAuthority> authorities;
        String singleRole = "NO_ROLE"; // Valor padrão se o conjunto de roles estiver vazio

        if (authentication != null && authentication.isAuthenticated() && token != null && token.equals(authentication.getCredentials())) {
            authorities = authentication.getAuthorities();
            // Extrair a primeira role (autorização) do usuário autenticado
            singleRole = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("NO_ROLE");
        }

        // Adicione o token ao userInfo, mesmo que não esteja autenticado
        Map<String, Object> userInfo = Map.of(
                "token", token
                // Adicione mais informações do usuário conforme necessário
        );

        return ResponseEntity.ok(userInfo);
    }
}
