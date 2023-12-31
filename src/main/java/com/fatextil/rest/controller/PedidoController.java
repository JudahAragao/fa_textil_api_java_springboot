package com.fatextil.rest.controller;

import com.fatextil.rest.dto.PedidoDto;
import com.fatextil.rest.form.PedidoForm;
import com.fatextil.rest.form.PedidoUpdateForm;
import com.fatextil.service.PedidoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<List<PedidoDto>> findAll() {
        List<PedidoDto> pedidoDtoList = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<PedidoDto> find(@PathVariable("id") long pedidoId) {
        PedidoDto pedidoDto = pedidoService.findById(pedidoId);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR')")
    public ResponseEntity<PedidoDto> insert(@Valid @RequestBody PedidoForm pedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PedidoDto pedidoDto = pedidoService.insert(pedidoForm);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDEDOR') or hasRole('ROLE_DESIGNER')")
    public ResponseEntity<PedidoDto> update(@Valid @RequestBody PedidoUpdateForm pedidoUpdateForm, @PathVariable("id") long pedidoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PedidoDto pedidoDto = pedidoService.update(pedidoUpdateForm, pedidoId);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long pedidoId) {
        pedidoService.delete(pedidoId);
        return ResponseEntity.noContent().build();
    }

}
