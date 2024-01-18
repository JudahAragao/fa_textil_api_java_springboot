package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ClientePFisicaDto;
import com.fatextil.rest.form.ClientePFisicaForm;
import com.fatextil.rest.form.ClientePFisicaUpdateForm;
import com.fatextil.service.ClientePFisicaService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<List<ClientePFisicaDto>> findAll() {
        List<ClientePFisicaDto> clientePFisicaDtoList = clientePFisicaService.findAll();
        return ResponseEntity.ok().body(clientePFisicaDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<ClientePFisicaDto> find(@PathVariable("id") long clienteId) {
        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.findById(clienteId);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<ClientePFisicaDto> insert(@Valid @RequestBody ClientePFisicaForm clientePFisicaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.insert(clientePFisicaForm);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<ClientePFisicaDto> update(@Valid @RequestBody ClientePFisicaUpdateForm clientePFisicaUpdateForm, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClientePFisicaDto clientePFisicaDto = clientePFisicaService.update(clientePFisicaUpdateForm, id);
        return ResponseEntity.ok().body(clientePFisicaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        clientePFisicaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
