package com.fatextil.rest.controller;

import com.fatextil.rest.dto.TamanhoProdutoDto;
import com.fatextil.rest.form.TamanhoProdutoForm;
import com.fatextil.service.TamanhoProdutoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tamanhoproduto")
public class TamanhoProdutoController {

    @Autowired
    private TamanhoProdutoService tamanhoProdutoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TamanhoProdutoDto>> findAll() {
        List<TamanhoProdutoDto> tamanhoProdutoDtoList = tamanhoProdutoService.findAll();
        return ResponseEntity.ok().body(tamanhoProdutoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TamanhoProdutoDto> find(@PathVariable("id") long tamanhoProdutoId) {
        TamanhoProdutoDto tamanhoProdutoDto = tamanhoProdutoService.findById(tamanhoProdutoId);
        return ResponseEntity.ok().body(tamanhoProdutoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TamanhoProdutoDto> insert(@Valid @RequestBody TamanhoProdutoForm tamanhoProdutoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TamanhoProdutoDto tamanhoProdutoDto = tamanhoProdutoService.insert(tamanhoProdutoForm);
        return ResponseEntity.ok().body(tamanhoProdutoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TamanhoProdutoDto> update(@Valid @RequestBody TamanhoProdutoForm tamanhoProdutoUpdateForm, @PathVariable("id") long tamanhoProdutoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TamanhoProdutoDto tamanhoProdutoDto = tamanhoProdutoService.update(tamanhoProdutoUpdateForm, tamanhoProdutoId);
        return ResponseEntity.ok().body(tamanhoProdutoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long tamanhoProdutoId) {
        tamanhoProdutoService.delete(tamanhoProdutoId);
        return ResponseEntity.noContent().build();
    }

}
