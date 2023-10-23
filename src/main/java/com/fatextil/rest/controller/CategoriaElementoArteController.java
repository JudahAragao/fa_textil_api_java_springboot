package com.fatextil.rest.controller;

import com.fatextil.rest.dto.CategoriaElementoArteDto;
import com.fatextil.rest.form.CategoriaElementoArteForm;
import com.fatextil.service.CategoriaElementoArteService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoriaelementoarte")
public class CategoriaElementoArteController {

    @Autowired
    private CategoriaElementoArteService categoriaElementoArteService;

    @GetMapping
    public ResponseEntity<List<CategoriaElementoArteDto>> findAll() {
        List<CategoriaElementoArteDto> categoriaElementoArteDtoList = categoriaElementoArteService.findAll();
        return ResponseEntity.ok().body(categoriaElementoArteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaElementoArteDto> find(@PathVariable("id") long categoriaElementoId) {
        CategoriaElementoArteDto categoriaElementoArteDto = categoriaElementoArteService.findById(categoriaElementoId);
        return ResponseEntity.ok().body(categoriaElementoArteDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaElementoArteDto> insert(@Valid @RequestBody CategoriaElementoArteForm categoriaElementoArteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        CategoriaElementoArteDto categoriaElementoArteDto = categoriaElementoArteService.insert(categoriaElementoArteForm);
        return ResponseEntity.ok().body(categoriaElementoArteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaElementoArteDto> update(@Valid @RequestBody CategoriaElementoArteForm categoriaElementoArteForm
            , @PathVariable("id") long categoriaElementoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        CategoriaElementoArteDto categoriaElementoArteDto = categoriaElementoArteService.update(categoriaElementoArteForm, categoriaElementoId);
        return ResponseEntity.ok().body(categoriaElementoArteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long categoriaElementoId) {
        categoriaElementoArteService.delete(categoriaElementoId);
        return ResponseEntity.noContent().build();
    }

}
