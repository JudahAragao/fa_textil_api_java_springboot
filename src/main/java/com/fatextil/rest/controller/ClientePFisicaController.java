package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ClientePFisicaDto;
import com.fatextil.rest.form.ClientePFisicaForm;
import com.fatextil.rest.form.ClientePFisicaUpdateForm;
import com.fatextil.service.ClientePFisicaService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientepfisica")
public class ClientePFisicaController {

    @Autowired
    private ClientePFisicaService clientePFisicaService;

    @GetMapping
    public ResponseEntity<List<ClientePFisicaDto>> findAll() {
        List<ClientePFisicaDto> clientePFisicaDtoList = clientePFisicaService.findAll();
        return ResponseEntity.ok().body(clientePFisicaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePFisicaDto> find(@PathVariable("id") long clienteId) {
        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.findById(clienteId);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @PostMapping
    public ResponseEntity<ClientePFisicaDto> insert(@Valid @RequestBody ClientePFisicaForm clientePFisicaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.insert(clientePFisicaForm);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientePFisicaDto> update(@Valid @RequestBody ClientePFisicaUpdateForm clientePFisicaUpdateForm, @PathVariable("id") long clienteId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.update(clientePFisicaUpdateForm, clienteId);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long clienteId) {
        clientePFisicaService.delete(clienteId);
        return ResponseEntity.noContent().build();
    }

}
