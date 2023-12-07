package com.fatextil.rest.controller;

import com.fatextil.rest.dto.StatusFabricacaoDto;
import com.fatextil.rest.form.StatusFabricacaoForm;
import com.fatextil.service.StatusFabricacaoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statusfabricacao")
public class StatusFabricacaoController {

    @Autowired
    private StatusFabricacaoService statusFabricacaoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<List<StatusFabricacaoDto>> findAll() {
        List<StatusFabricacaoDto> statusFabricacaoDtoList = statusFabricacaoService.findAll();
        return ResponseEntity.ok().body(statusFabricacaoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<StatusFabricacaoDto> find(@PathVariable("id") long statusFabricacaoId) {
        StatusFabricacaoDto statusFabricacaoDto = statusFabricacaoService.findById(statusFabricacaoId);
        return ResponseEntity.ok().body(statusFabricacaoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StatusFabricacaoDto> insert(@Valid @RequestBody StatusFabricacaoForm statusFabricacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        StatusFabricacaoDto statusFabricacaoDto = statusFabricacaoService.insert(statusFabricacaoForm);
        return ResponseEntity.ok().body(statusFabricacaoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StatusFabricacaoDto> update(@Valid @RequestBody StatusFabricacaoForm statusFabricacaoUpdateForm, @PathVariable("id") long statusFabricacaoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        StatusFabricacaoDto statusFabricacaoDto = statusFabricacaoService.update(statusFabricacaoUpdateForm, statusFabricacaoId);
        return ResponseEntity.ok().body(statusFabricacaoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long statusFabricacaoId) {
        statusFabricacaoService.delete(statusFabricacaoId);
        return ResponseEntity.noContent().build();
    }

}
