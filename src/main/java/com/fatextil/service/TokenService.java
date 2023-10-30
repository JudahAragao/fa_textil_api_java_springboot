package com.fatextil.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fatextil.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarToken(UsuarioModel usuario) {

        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getUsuarioId())
                .withExpiresAt(LocalDateTime.now()
                        .plusSeconds(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));

    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();

    }
}
