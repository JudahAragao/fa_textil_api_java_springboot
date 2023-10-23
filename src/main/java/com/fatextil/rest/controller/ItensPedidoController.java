package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ItensPedidoDto;
import com.fatextil.rest.form.ItensPedidoForm;
import com.fatextil.rest.form.ItensPedidoUpdateForm;
import com.fatextil.service.ItensPedidoService;
import com.fatextil.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itenspedido")
public class ItensPedidoController {

    @Autowired
    private ItensPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItensPedidoDto>> findAll() {
        List<ItensPedidoDto> itemPedidoDtoList = itemPedidoService.findAll();
        return ResponseEntity.ok().body(itemPedidoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensPedidoDto> find(@PathVariable("id") long itemPedidoId) {
        ItensPedidoDto itemPedidoDto = itemPedidoService.findById(itemPedidoId);
        return ResponseEntity.ok().body(itemPedidoDto);
    }

    @PostMapping
    public ResponseEntity<ItensPedidoDto> insert(@Valid @RequestBody ItensPedidoForm itemPedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ItensPedidoDto itemPedidoDto = itemPedidoService.insert(itemPedidoForm);
        return ResponseEntity.ok().body(itemPedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensPedidoDto> update(@Valid @RequestBody ItensPedidoUpdateForm itemPedidoUpdateForm, @PathVariable("id") long itemPedidoId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ItensPedidoDto itemPedidoDto = itemPedidoService.update(itemPedidoUpdateForm, itemPedidoId);
        return ResponseEntity.ok().body(itemPedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long itemPedidoId) {
        itemPedidoService.delete(itemPedidoId);
        return ResponseEntity.noContent().build();
    }

}
