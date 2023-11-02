package com.fatextil.rest.controller;

import com.fatextil.model.UsuarioModel;
import com.fatextil.rest.dto.LoginDto;
import com.fatextil.rest.dto.UsuarioDto;
import com.fatextil.rest.form.UsuarioForm;
import com.fatextil.rest.form.UsuarioUpdateForm;
import com.fatextil.service.TokenService;
import com.fatextil.service.UsuarioService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> usuarioDtoList = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarioDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioDto> find(@PathVariable("id") long usuarioId) {
        UsuarioDto usuarioDto = usuarioService.findById(usuarioId);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioDto> insert(@Valid @RequestBody UsuarioForm usuarioForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UsuarioDto usuarioDto = usuarioService.insert(usuarioForm);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto login){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var usuario = (UsuarioModel) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<UsuarioDto> update(@Valid @RequestBody UsuarioUpdateForm usuarioUpdateForm, @PathVariable("id") long usuarioId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UsuarioDto usuarioDto = usuarioService.update(usuarioUpdateForm, usuarioId);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long usuarioId) {
        usuarioService.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }

}
