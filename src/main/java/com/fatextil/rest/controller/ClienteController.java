package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ClienteDto;
import com.fatextil.rest.form.ClienteForm;
import com.fatextil.service.ClienteService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<ClienteDto> clienteDtoList = clienteService.findAll();
        return ResponseEntity.ok().body(clienteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> find(@PathVariable("id") long clienteId) {
        ClienteDto clienteDto = clienteService.findById(clienteId);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> insert(@Valid @RequestBody ClienteForm clienteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClienteDto clienteDto = clienteService.insert(clienteForm);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@Valid @RequestBody ClienteForm clienteForm, @PathVariable("id") long clienteId, BindingResult br) {
        if (br.hasErrors()) {
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        }

        ClienteDto clienteDto = clienteService.update(clienteForm, clienteId);
        return ResponseEntity.ok().body(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long clienteId) {
        clienteService.delete(clienteId);
        return ResponseEntity.noContent().build();
    }

}
