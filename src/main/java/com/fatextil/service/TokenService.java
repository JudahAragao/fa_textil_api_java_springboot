package com.fatextil.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fatextil.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Service
public class TokenService {

    private static final byte[] secretKey = generateSecretKey(256);
    private static final String CHAVE_SECRETA = Base64.getEncoder().encodeToString(secretKey);;

    public String gerarToken(UsuarioModel usuario) {

        return JWT.create()
                .withIssuer("fatextil-api")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getUsuarioId())
                .withClaim("perfilAcessoId", usuario.getPerfilAcessoId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(60)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(CHAVE_SECRETA));

    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256(CHAVE_SECRETA))
                .withIssuer("fatextil-api")
                .build().verify(token).getSubject();

    }

    private static byte[] generateSecretKey(int keyLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[keyLength / 8];
        secureRandom.nextBytes(key);
        return key;
    }
}
