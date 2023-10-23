package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ClientePJuridicaDto;
import com.fatextil.rest.form.ClientePJuridicaForm;
import com.fatextil.rest.form.ClientePJuridicaUpdateForm;
import com.fatextil.service.ClientePJuridicaService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientepjuridica")
public class ClientePJuridicaController {

    @Autowired
    private ClientePJuridicaService clientePJuridicaService;

    @GetMapping
    public ResponseEntity<List<ClientePJuridicaDto>> findAll() {
        List<ClientePJuridicaDto> clientePJuridicaDtoList = clientePJuridicaService.findAll();
        return ResponseEntity.ok().body(clientePJuridicaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePJuridicaDto> find(@PathVariable("id") long clienteId) {
        ClientePJuridicaDto clientePJuridicaDto = clientePJuridicaService.findById(clienteId);
        return ResponseEntity.ok().body(clientePJuridicaDto);
    }

    @PostMapping
    public ResponseEntity<ClientePJuridicaDto> insert(@Valid @RequestBody ClientePJuridicaForm clientePJuridicaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePJuridicaDto clientePJuridicaDto = clientePJuridicaService.insert(clientePJuridicaForm);
        return ResponseEntity.ok().body(clientePJuridicaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientePJuridicaDto> update(@Valid @RequestBody ClientePJuridicaUpdateForm clientePJuridicaUpdateForm, @PathVariable("id") long clienteId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePJuridicaDto clientePJuridicaDto = clientePJuridicaService.update(clientePJuridicaUpdateForm, clienteId);
        return ResponseEntity.ok().body(clientePJuridicaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long clienteId) {
        clientePJuridicaService.delete(clienteId);
        return ResponseEntity.noContent().build();
    }

}
