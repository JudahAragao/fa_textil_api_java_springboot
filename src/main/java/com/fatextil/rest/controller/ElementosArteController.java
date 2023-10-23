package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ElementosArteDto;
import com.fatextil.rest.form.ElementosArteForm;
import com.fatextil.service.ElementosArteService;
import com.fatextil.service.exceptions.ConstraintException;
import com.fatextil.storage.Disco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elementosarte")
public class ElementosArteController {

    @Autowired
    private ElementosArteService elementoArteService;

    @GetMapping
    public ResponseEntity<List<ElementosArteDto>> findAll() {
        List<ElementosArteDto> elementoArteDtoList = elementoArteService.findAll();
        return ResponseEntity.ok().body(elementoArteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementosArteDto> find(@PathVariable("id") long elementoArteId) {
        ElementosArteDto elementoArteDto = elementoArteService.findById(elementoArteId);
        return ResponseEntity.ok().body(elementoArteDto);
    }

    private Disco disco;

    @PostMapping
    public void upload (@Valid @RequestParam MultipartFile elemento) {
        disco.salvarFoto(elemento);
    }

    @PostMapping("/upload")
    public ResponseEntity<ElementosArteDto> insert(@Valid @RequestBody ElementosArteForm elementosArteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementosArteDto elementosArteDto = elementoArteService.insert(elementosArteForm);
        return ResponseEntity.ok().body(elementosArteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementosArteDto> update(@Valid @RequestBody ElementosArteForm elementosArteUpdateForm, @PathVariable("id") long elementoArteId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementosArteDto elementoArteDto = elementoArteService.update(elementosArteUpdateForm, elementoArteId);
        return ResponseEntity.ok().body(elementoArteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long elementoArteId) {
        elementoArteService.delete(elementoArteId);
        return ResponseEntity.noContent().build();
    }

}
