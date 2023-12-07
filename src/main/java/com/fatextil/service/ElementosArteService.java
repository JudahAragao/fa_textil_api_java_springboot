package com.fatextil.service;

import com.fatextil.model.ElementosArteModel;
import com.fatextil.repository.ElementosArteRepository;
import com.fatextil.rest.form.ElementosArteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ElementosArteService {

    @Autowired
    private final ElementosArteRepository elementosArteRepository;

    public ElementosArteService(ElementosArteRepository elementosArteRepository) {
        this.elementosArteRepository = elementosArteRepository;
    }

    public ElementosArteModel uploadFile(ElementosArteForm elementosArteForm) throws IOException {
        MultipartFile file = elementosArteForm.getData();
        ElementosArteModel elementosArteModel = new ElementosArteModel();
        elementosArteModel.setFileName(elementosArteForm.getFileName());
        elementosArteModel.setContentType(file.getContentType());
        elementosArteModel.setData(file.getBytes());

        return elementosArteRepository.save(elementosArteModel);
    }

    public ElementosArteModel updateFile(Long id, ElementosArteForm elementosArteForm) throws IOException {
        ElementosArteModel existingFile = elementosArteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + id));
        existingFile.setFileName(elementosArteForm.getFileName());
        existingFile.setContentType(elementosArteForm.getData().getContentType());
        existingFile.setData(elementosArteForm.getData().getBytes());

        return elementosArteRepository.save(existingFile);
    }

    public ElementosArteModel getFile(Long id) {
        return elementosArteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + id));
    }

    public List<ElementosArteModel> getAllFiles() {
        return elementosArteRepository.findAll();
    }

    public void deleteFile(Long id) {
        elementosArteRepository.deleteById(id);
    }
}
