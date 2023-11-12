package com.fatextil.rest.controller;

import com.fatextil.rest.dto.PerfilAcessoDto;
import com.fatextil.rest.form.PerfilAcessoForm;
import com.fatextil.service.PerfilAcessoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/perfilacesso")
public class PerfilAcessoController {

    @Autowired
    private PerfilAcessoService perfilAcessoService;

    @GetMapping
    public ResponseEntity<List<PerfilAcessoDto>> findAll() {
        List<PerfilAcessoDto> perfilAcessoDtoList = perfilAcessoService.findAll();
        return ResponseEntity.ok().body(perfilAcessoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerfilAcessoDto> find(@PathVariable("id") long perfilAcessoId) {
        PerfilAcessoDto perfilAcessoDto = perfilAcessoService.findById(perfilAcessoId);
        return ResponseEntity.ok().body(perfilAcessoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerfilAcessoDto> insert(@Valid @RequestBody PerfilAcessoForm perfilAcessoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PerfilAcessoDto perfilAcessoDto = perfilAcessoService.insert(perfilAcessoForm);
        return ResponseEntity.ok().body(perfilAcessoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerfilAcessoDto> update(@RequestBody PerfilAcessoForm perfilAcessoUpdateForm
            , @PathVariable("id") long perfilAcessoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PerfilAcessoDto perfilAcessoDto = perfilAcessoService.update(perfilAcessoUpdateForm, perfilAcessoId);
        return ResponseEntity.ok().body(perfilAcessoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long perfilAcessoId) {
        perfilAcessoService.delete(perfilAcessoId);
        return ResponseEntity.noContent().build();
    }

}
