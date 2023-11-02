package com.fatextil.rest.controller;

import com.fatextil.rest.dto.EtapasFabricacaoDto;
import com.fatextil.rest.form.EtapasFabricacaoForm;
import com.fatextil.service.EtapasFabricacaoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/etapasfabricacao")
public class EtapasFabricacaoController {

    @Autowired
    private EtapasFabricacaoService etapasFabricacaoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<List<EtapasFabricacaoDto>> findAll() {
        List<EtapasFabricacaoDto> etapasFabricacaoDtoList = etapasFabricacaoService.findAll();
        return ResponseEntity.ok().body(etapasFabricacaoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<EtapasFabricacaoDto> find(@PathVariable("id") long etapaId) {
        EtapasFabricacaoDto etapasFabricacaoDto = etapasFabricacaoService.findById(etapaId);
        return ResponseEntity.ok().body(etapasFabricacaoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EtapasFabricacaoDto> insert(@Valid @RequestBody EtapasFabricacaoForm etapasFabricacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        EtapasFabricacaoDto etapasFabricacaoDto = etapasFabricacaoService.insert(etapasFabricacaoForm);
        return ResponseEntity.ok().body(etapasFabricacaoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EtapasFabricacaoDto> update(@Valid @RequestBody EtapasFabricacaoForm etapasFabricacaoUpdateForm, @PathVariable("id") long etapaId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        EtapasFabricacaoDto etapasFabricacaoDto = etapasFabricacaoService.update(etapasFabricacaoUpdateForm, etapaId);
        return ResponseEntity.ok().body(etapasFabricacaoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long etapaId) {
        etapasFabricacaoService.delete(etapaId);
        return ResponseEntity.noContent().build();
    }

}
