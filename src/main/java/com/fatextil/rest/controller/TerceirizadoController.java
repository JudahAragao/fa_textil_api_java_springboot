package com.fatextil.rest.controller;

import com.fatextil.rest.dto.TerceirizadoDto;
import com.fatextil.rest.form.TerceirizadoForm;
import com.fatextil.rest.form.TerceirizadoUpdateForm;
import com.fatextil.service.TerceirizadoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/terceirizado")
public class TerceirizadoController {

    @Autowired
    private TerceirizadoService terceirizadoService;

    @GetMapping
    public ResponseEntity<List<TerceirizadoDto>> findAll() {
        List<TerceirizadoDto> terceirizadoDtoList = terceirizadoService.findAll();
        return ResponseEntity.ok().body(terceirizadoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerceirizadoDto> find(@PathVariable("id") long terceirizadoId) {
        TerceirizadoDto terceirizadoDto = terceirizadoService.findById(terceirizadoId);
        return ResponseEntity.ok().body(terceirizadoDto);
    }

    @PostMapping
    public ResponseEntity<TerceirizadoDto> insert(@Valid @RequestBody TerceirizadoForm terceirizadoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TerceirizadoDto terceirizadoDto = terceirizadoService.insert(terceirizadoForm);
        return ResponseEntity.ok().body(terceirizadoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerceirizadoDto> update(@Valid @RequestBody TerceirizadoUpdateForm terceirizadoUpdateForm, @PathVariable("id") long terceirizadoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TerceirizadoDto terceirizadoDto = terceirizadoService.update(terceirizadoUpdateForm, terceirizadoId);
        return ResponseEntity.ok().body(terceirizadoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long terceirizadoId) {
        terceirizadoService.delete(terceirizadoId);
        return ResponseEntity.noContent().build();
    }

}
