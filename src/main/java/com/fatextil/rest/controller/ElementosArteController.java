package com.fatextil.rest.controller;

import com.fatextil.model.ElementosArteModel;
import com.fatextil.rest.form.ElementosArteForm;
import com.fatextil.service.ElementosArteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/elementosarte")
public class ElementosArteController {

    @Autowired
    private final ElementosArteService elementosArteService;

    public ElementosArteController(ElementosArteService elementoArteService){
        this.elementosArteService = elementoArteService;
    }

    @PostMapping
    public ResponseEntity<ElementosArteModel> uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute ElementosArteForm elementosArteForm) throws IOException {
        elementosArteForm.setData(file);
        ElementosArteModel uploadedFile = elementosArteService.uploadFile(elementosArteForm);
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        ElementosArteModel file = elementosArteService.getFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDispositionFormData("attachment", file.getFileName());

        return new ResponseEntity<>(file.getData(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ElementosArteModel>> getAllFiles() {
        List<ElementosArteModel> files = elementosArteService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementosArteModel> updateFile(@RequestParam("file") MultipartFile file, @PathVariable Long id, @ModelAttribute ElementosArteForm elementosArteForm) throws IOException {
        elementosArteForm.setData(file);
        ElementosArteModel updatedFile = elementosArteService.updateFile(id, elementosArteForm);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        elementosArteService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
