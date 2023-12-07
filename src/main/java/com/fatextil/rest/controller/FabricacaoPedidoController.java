package com.fatextil.rest.controller;

import com.fatextil.rest.dto.FabricacaoPedidoDto;
import com.fatextil.rest.form.FabricacaoPedidoForm;
import com.fatextil.service.FabricacaoPedidoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fabricacaopedido")
public class FabricacaoPedidoController {

    @Autowired
    private FabricacaoPedidoService fabricacaoPedidoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FabricacaoPedidoDto>> findAll() {
        List<FabricacaoPedidoDto> fabricacaoPedidoDtoList = fabricacaoPedidoService.findAll();
        return ResponseEntity.ok().body(fabricacaoPedidoDtoList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FabricacaoPedidoDto> find(@PathVariable("id") long fabricacaoPedidoId) {
        FabricacaoPedidoDto fabricacaoPedidoDto = fabricacaoPedidoService.findById(fabricacaoPedidoId);
        return ResponseEntity.ok().body(fabricacaoPedidoDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FabricacaoPedidoDto> insert(@Valid @RequestBody FabricacaoPedidoForm fabricacaoPedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FabricacaoPedidoDto fabricacaoPedidoDto = fabricacaoPedidoService.insert(fabricacaoPedidoForm);
        return ResponseEntity.ok().body(fabricacaoPedidoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FabricacaoPedidoDto> update(@Valid @RequestBody FabricacaoPedidoForm fabricacaoPedidoUpdateForm, @PathVariable("id") long fabricacaoPedidoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FabricacaoPedidoDto fabricacaoPedidoDto = fabricacaoPedidoService.update(fabricacaoPedidoUpdateForm, fabricacaoPedidoId);
        return ResponseEntity.ok().body(fabricacaoPedidoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long fabricacaoPedidoId) {
        fabricacaoPedidoService.delete(fabricacaoPedidoId);
        return ResponseEntity.noContent().build();
    }

}
