package com.fatextil.rest.controller;

import com.fatextil.rest.dto.TerceirizacaoDto;
import com.fatextil.rest.form.TerceirizacaoForm;
import com.fatextil.service.TerceirizacaoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/terceirizacao")
public class TerceirizacaoController {

    @Autowired
    private TerceirizacaoService terceirizacaoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TerceirizacaoDto>> findAll() {
        List<TerceirizacaoDto> terceirizacaoDtoList = terceirizacaoService.findAll();
        return ResponseEntity.ok().body(terceirizacaoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TerceirizacaoDto> find(@PathVariable("id") long terceirizacaoId) {
        TerceirizacaoDto terceirizacaoDto = terceirizacaoService.findById(terceirizacaoId);
        return ResponseEntity.ok().body(terceirizacaoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TerceirizacaoDto> insert(@Valid @RequestBody TerceirizacaoForm terceirizacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TerceirizacaoDto terceirizacaoDto = terceirizacaoService.insert(terceirizacaoForm);
        return ResponseEntity.ok().body(terceirizacaoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TerceirizacaoDto> update(@Valid @RequestBody TerceirizacaoForm terceirizacaoUpdateForm, @PathVariable("id") long terceirizacaoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TerceirizacaoDto terceirizacaoDto = terceirizacaoService.update(terceirizacaoUpdateForm, terceirizacaoId);
        return ResponseEntity.ok().body(terceirizacaoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long terceirizacaoId) {
        terceirizacaoService.delete(terceirizacaoId);
        return ResponseEntity.noContent().build();
    }

}
