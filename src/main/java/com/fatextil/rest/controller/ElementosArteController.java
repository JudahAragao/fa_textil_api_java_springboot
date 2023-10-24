package com.fatextil.rest.controller;

import com.fatextil.rest.dto.ElementosArteDto;
import com.fatextil.rest.form.ElementosArteForm;
import com.fatextil.service.ElementosArteService;
import com.fatextil.service.exceptions.ConstraintException;
import com.fatextil.storage.Disco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elementosarte")
public class ElementosArteController {

    @Autowired
    private ElementosArteService elementoArteService;

    @Autowired
    private Disco disco;

    @Value("${fatextil.disco.diretorio-fotos}")
    private String diretorio;

    @GetMapping
    public ResponseEntity<List<ElementosArteDto>> findAll() {
        List<ElementosArteDto> elementoArteDtoList = elementoArteService.findAll();
        return ResponseEntity.ok().body(elementoArteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementosArteDto> find(@PathVariable("id") long elementoArteId) {
        ElementosArteDto elementoArteDto = elementoArteService.findById(elementoArteId);
        return ResponseEntity.ok().body(elementoArteDto);
    }

    @PostMapping("/upload")
    public ResponseEntity<ElementosArteDto> upload(@RequestParam("elemento") MultipartFile elemento) {
        try {
            // Obtém o nome do arquivo original
            String originalFileName = elemento.getOriginalFilename();

            if (originalFileName != null) {
                String fileName = addTimestampToFileName(originalFileName); // Adicione a data e hora ao nome do arquivo
                String filePath = diretorio;

                // Verifique se o arquivo já existe no banco de dados
                ElementosArteDto existingFile = elementoArteService.findByFileName(fileName);

                if (existingFile != null) {
                    // Arquivo já existe no banco de dados, você pode retornar um erro aqui
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }

                disco.salvarFoto(elemento);

                // Chama o serviço para realizar o upload do arquivo e salvar no banco de dados
                ElementosArteDto uploadedDto = elementoArteService.upload(elemento, fileName, filePath);

                return ResponseEntity.ok(uploadedDto); // Retorna o DTO preenchido
            } else {
                // Lida com o caso em que o nome do arquivo original é nulo
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            // Lida com exceções, se necessário
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ElementosArteDto> update(@Valid @RequestBody ElementosArteForm elementosArteUpdateForm, @PathVariable("id") long elementoArteId, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementosArteDto elementoArteDto = elementoArteService.update(elementosArteUpdateForm, elementoArteId);
        return ResponseEntity.ok().body(elementoArteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long elementoArteId) {
        elementoArteService.delete(elementoArteId);
        return ResponseEntity.noContent().build();
    }

    private String addTimestampToFileName(String fileName) {
        // Concatene a data e hora ao nome do arquivo
        String timestamp = "_" + System.currentTimeMillis(); // Use o tempo atual como timestamp
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            // Se o arquivo tem uma extensão, insira o timestamp antes da extensão
            String nameWithoutExtension = fileName.substring(0, lastDotIndex);
            String extension = fileName.substring(lastDotIndex);
            return nameWithoutExtension + timestamp + extension;
        } else {
            // Se o arquivo não tem extensão, apenas adicione o timestamp ao nome
            return fileName + timestamp;
        }
    }

}
