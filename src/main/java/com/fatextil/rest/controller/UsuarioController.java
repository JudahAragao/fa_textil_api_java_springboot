package com.fatextil.rest.controller;

import com.fatextil.rest.dto.UsuarioDto;
import com.fatextil.rest.form.UsuarioForm;
import com.fatextil.rest.form.UsuarioUpdateForm;
import com.fatextil.service.UsuarioService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> usuarioDtoList = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarioDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> find(@PathVariable("id") long usuarioId) {
        UsuarioDto usuarioDto = usuarioService.findById(usuarioId);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> insert(@Valid @RequestBody UsuarioForm usuarioForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UsuarioDto usuarioDto = usuarioService.insert(usuarioForm);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@Valid @RequestBody UsuarioUpdateForm usuarioUpdateForm, @PathVariable("id") long usuarioId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UsuarioDto usuarioDto = usuarioService.update(usuarioUpdateForm, usuarioId);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long usuarioId) {
        usuarioService.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }

}
