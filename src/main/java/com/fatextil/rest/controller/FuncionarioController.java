package com.fatextil.rest.controller;

import com.fatextil.rest.dto.FuncionarioDto;
import com.fatextil.rest.form.FuncionarioForm;
import com.fatextil.rest.form.FuncionarioUpdateForm;
import com.fatextil.service.FuncionarioService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FuncionarioDto>> findAll() {
        List<FuncionarioDto> funcionarioDtoList = funcionarioService.findAll();
        return ResponseEntity.ok().body(funcionarioDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FuncionarioDto> find(@PathVariable("id") long funcionarioId) {
        FuncionarioDto funcionarioDto = funcionarioService.findById(funcionarioId);
        return ResponseEntity.ok().body(funcionarioDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FuncionarioDto> insert(@Valid @RequestBody FuncionarioForm funcionarioForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FuncionarioDto funcionarioDto = funcionarioService.insert(funcionarioForm);
        return ResponseEntity.ok().body(funcionarioDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FuncionarioDto> update(@Valid @RequestBody FuncionarioUpdateForm funcionarioUpdateForm
            , @PathVariable("id") long funcionarioId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FuncionarioDto funcionarioDto = funcionarioService.update(funcionarioUpdateForm, funcionarioId);
        return ResponseEntity.ok().body(funcionarioDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long funcionarioId) {
        funcionarioService.delete(funcionarioId);
        return ResponseEntity.noContent().build();
    }

}
