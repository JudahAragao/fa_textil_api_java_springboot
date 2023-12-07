package com.fatextil.rest.controller;

import com.fatextil.rest.dto.StatusPedidoDto;
import com.fatextil.rest.form.StatusPedidoForm;
import com.fatextil.service.StatusPedidoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statuspedido")
public class StatusPedidoController {

    @Autowired
    private StatusPedidoService statusPedidoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<List<StatusPedidoDto>> findAll() {
        List<StatusPedidoDto> statusPedidoDtoList = statusPedidoService.findAll();
        return ResponseEntity.ok().body(statusPedidoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<StatusPedidoDto> find(@PathVariable("id") long statusPedidoId) {
        StatusPedidoDto statusPedidoDto = statusPedidoService.findById(statusPedidoId);
        return ResponseEntity.ok().body(statusPedidoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StatusPedidoDto> insert(@Valid @RequestBody StatusPedidoForm statusPedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        StatusPedidoDto statusPedidoDto = statusPedidoService.insert(statusPedidoForm);
        return ResponseEntity.ok().body(statusPedidoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StatusPedidoDto> update(@Valid @RequestBody StatusPedidoForm statusPedidoUpdateForm, @PathVariable("id") long statusPedidoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        StatusPedidoDto statusPedidoDto = statusPedidoService.update(statusPedidoUpdateForm, statusPedidoId);
        return ResponseEntity.ok().body(statusPedidoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long statusPedidoId) {
        statusPedidoService.delete(statusPedidoId);
        return ResponseEntity.noContent().build();
    }

}
