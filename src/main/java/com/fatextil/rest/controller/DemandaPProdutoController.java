package com.fatextil.rest.controller;

import com.fatextil.rest.dto.DemandaPProdutoDto;
import com.fatextil.rest.form.DemandaPProdutoForm;
import com.fatextil.service.DemandaPProdutoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demandapproduto")
public class DemandaPProdutoController {

    @Autowired
    private DemandaPProdutoService demandaPProdutoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DemandaPProdutoDto>> findAll() {
        List<DemandaPProdutoDto> demandaPProdutoDtoList = demandaPProdutoService.findAll();
        return ResponseEntity.ok().body(demandaPProdutoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DemandaPProdutoDto> find(@PathVariable("id") long demandaId) {
        DemandaPProdutoDto demandaPProdutoDto = demandaPProdutoService.findById(demandaId);
        return ResponseEntity.ok().body(demandaPProdutoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DemandaPProdutoDto> insert(@Valid @RequestBody DemandaPProdutoForm demandaPProdutoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        DemandaPProdutoDto demandaPProdutoDto = demandaPProdutoService.insert(demandaPProdutoForm);
        return ResponseEntity.ok().body(demandaPProdutoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DemandaPProdutoDto> update(@Valid @RequestBody DemandaPProdutoForm demandaPProdutoUpdateForm, @PathVariable("id") long demandaId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        DemandaPProdutoDto demandaPProdutoDto = demandaPProdutoService.update(demandaPProdutoUpdateForm, demandaId);
        return ResponseEntity.ok().body(demandaPProdutoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long demandaId) {
        demandaPProdutoService.delete(demandaId);
        return ResponseEntity.noContent().build();
    }

}
