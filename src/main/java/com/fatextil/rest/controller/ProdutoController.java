package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ProdutoDto;
import com.fatextil.rest.form.ProdutoForm;
import com.fatextil.service.ProdutoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<ProdutoDto> produtoDtoList = produtoService.findAll();
        return ResponseEntity.ok().body(produtoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<ProdutoDto> find(@PathVariable("id") long produtoId) {
        ProdutoDto produtoDto = produtoService.findById(produtoId);
        return ResponseEntity.ok().body(produtoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProdutoDto> insert(@Valid @RequestBody ProdutoForm produtoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProdutoDto produtoDto = produtoService.insert(produtoForm);
        return ResponseEntity.ok().body(produtoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProdutoDto> update(@Valid @RequestBody ProdutoForm produtoUpdateForm, @PathVariable("id") long produtoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProdutoDto produtoDto = produtoService.update(produtoUpdateForm, produtoId);
        return ResponseEntity.ok().body(produtoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long produtoId) {
        produtoService.delete(produtoId);
        return ResponseEntity.noContent().build();
    }

}
