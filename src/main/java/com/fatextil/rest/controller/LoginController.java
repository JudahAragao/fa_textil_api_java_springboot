package com.fatextil.rest.controller;

import com.fatextil.model.UsuarioModel;
import com.fatextil.rest.dto.LoginDto;
import com.fatextil.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody LoginDto login){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var usuario = (UsuarioModel) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario);
    }

}
