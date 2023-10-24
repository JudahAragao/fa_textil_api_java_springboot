package com.fatextil.service;

import com.fatextil.model.CategoriaElementoArteModel;
import com.fatextil.model.ClienteModel;
import com.fatextil.model.ElementosArteModel;
import com.fatextil.model.ItensPedidoModel;
import com.fatextil.repository.ElementosArteRepository;
import com.fatextil.rest.dto.ElementosArteDto;
import com.fatextil.rest.form.ElementosArteForm;
import com.fatextil.service.exceptions.DataIntegrityException;
import com.fatextil.service.exceptions.ObjectNotFoundException;
import com.fatextil.service.exceptions.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ElementosArteService {

    @Autowired
    private ElementosArteRepository elementosArteRepository;

    public ElementosArteDto findByFileName(String fileName) {
        Optional<ElementosArteModel> optionalElementosArteModel = elementosArteRepository.findByFileName(fileName);

        return optionalElementosArteModel.map(this::convertModelToDto).orElse(null);
    }


    // Busca completa no banco de dados
    public List<ElementosArteDto> findAll() {
        List<ElementosArteModel> elementosArteList = elementosArteRepository.findAll();
        return convertListToDto(elementosArteList);
    }

    // Busca no banco de dados
    public ElementosArteDto findById(Long elementosArteId) {
        try {
            ElementosArteModel elementosArteModel = elementosArteRepository.findById(elementosArteId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + elementosArteId + " Tipo: " + ElementosArteModel.class.getName()));

            return convertModelToDto(elementosArteModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + elementosArteId + " Tipo: " + ElementosArteModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public ElementosArteDto insert(ElementosArteForm elementosArteForm) {
        try {
            ElementosArteModel elementosArteNovo = convertFormToModel(elementosArteForm);
            Optional<ElementosArteModel> byFileName = elementosArteRepository.findByFileName(elementosArteNovo.getFileName());

            if (byFileName.isPresent())
                throw new IllegalStateException("Arquivo já existe, por favor mude o nome do arquivo que esta tentando enviar!");

            elementosArteNovo = elementosArteRepository.save(elementosArteNovo);
            return convertModelToDto(elementosArteNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }
    public ElementosArteDto upload(MultipartFile elemento, String fileName, String filePath) {
        try {
            Optional<ElementosArteModel> optionalExistingFile = elementosArteRepository.findByFileName(fileName);

            if (optionalExistingFile.isPresent()) {
                ElementosArteModel existingFile = optionalExistingFile.get();

                // Concatene a data de modificação ao nome do arquivo
                String modifiedFileName = fileName + "_" + new Date().getTime();
                existingFile.setFileName(modifiedFileName);

                // Atualize os metadados, se necessário
                // Salve novamente no repositório se desejado
                elementosArteRepository.save(existingFile);
                // Use a função de conversão para converter ElementosArteModel em ElementosArteDto e retorne imediatamente
                return convertModelToDto(existingFile);
            }

            // Salva o arquivo no banco de dados
            saveFileToDatabase(fileName, filePath);

            // Crie um DTO para a resposta
            ElementosArteDto responseDto = new ElementosArteDto();
            responseDto.setFilename(fileName); // Preencha os valores necessários no DTO
            responseDto.setPath(filePath);

            return responseDto;
        } catch (Exception e) {
            // Lida com exceções, se necessário
            throw new UploadException("Falha no upload do arquivo.", e);
        }
    }


    // Alteração no banco de dados
    public ElementosArteDto update(ElementosArteForm elementosArteForm, Long elementosArteId) {
        try {
            Optional<ElementosArteModel> elementosArteExistente = elementosArteRepository.findById(elementosArteId);
            if (elementosArteExistente.isPresent()) {
                ElementosArteModel elementosArteAtualizado = elementosArteExistente.get();

                // Para criar uma instância do ItensPedidoModel
                ItensPedidoModel itensPedidoModel = new ItensPedidoModel();
                itensPedidoModel.setItensPedidoId(elementosArteForm.getItensPedidoId());
                elementosArteAtualizado.setItensPedidoId(itensPedidoModel);

                // Para criar uma instância do CategoriaElementoArteModel
                CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
                categoriaElementoArteModel.setCategoriaElementoId(elementosArteForm.getCategoriaElementoId());
                elementosArteAtualizado.setCategoriaElementoId(categoriaElementoArteModel);

                elementosArteAtualizado.setFileName(elementosArteForm.getFilename());
                elementosArteAtualizado.setPath(elementosArteForm.getPath());

                elementosArteRepository.save(elementosArteAtualizado);
                return convertModelToDto(elementosArteAtualizado);
            } else {
                throw new DataIntegrityException("O ID do elementosArte não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) de ElementosArte não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long elementosArteId) {
        try {
            if (elementosArteRepository.existsById(elementosArteId)) {
                elementosArteRepository.deleteById(elementosArteId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um ElementosArte!");
        }
    }

    // Conversores de FORM para MODEL
    public ElementosArteModel convertFormToModel(ElementosArteForm elementosArteForm) {
        ElementosArteModel elementosArteModel = new ElementosArteModel();

        // Verifique se os campos não são nulos antes de acessá-los
        if (elementosArteForm.getItensPedidoId() != null) {
            ItensPedidoModel itensPedidoModel = new ItensPedidoModel();
            itensPedidoModel.setItensPedidoId(elementosArteForm.getItensPedidoId());
            elementosArteModel.setItensPedidoId(itensPedidoModel);
        }

        if (elementosArteForm.getCategoriaElementoId() != null) {
            CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
            categoriaElementoArteModel.setCategoriaElementoId(elementosArteForm.getCategoriaElementoId());
            elementosArteModel.setCategoriaElementoId(categoriaElementoArteModel);
        }

        elementosArteModel.setFileName(elementosArteForm.getFilename());
        elementosArteModel.setPath(elementosArteForm.getPath());

        return elementosArteModel;
    }

    // Conversores de MODEL para DTO
    public ElementosArteDto convertModelToDto(ElementosArteModel elementosArteModel) {
        ElementosArteDto elementosArteDto = new ElementosArteDto();

        elementosArteDto.setElementosArteId(elementosArteModel.getElementosArteId());
        elementosArteDto.setItensPedidoId(elementosArteModel.getItensPedidoId().getItensPedidoId());
        elementosArteDto.setCategoriaElementoId(elementosArteModel.getCategoriaElementoId().getCategoriaElementoId());
        elementosArteDto.setFilename(elementosArteModel.getFileName());
        elementosArteDto.setPath(elementosArteModel.getPath());

        return elementosArteDto;
    }

    // Conversor de LIST para DTO
    public List<ElementosArteDto> convertListToDto(List<ElementosArteModel> elementosArteList) {
        List<ElementosArteDto> elementosArteDtoList = new ArrayList<>();
        for (ElementosArteModel elementosArteModel : elementosArteList) {
            ElementosArteDto elementosArteDto = convertModelToDto(elementosArteModel);
            elementosArteDtoList.add(elementosArteDto);
        }
        return elementosArteDtoList;
    }

    // Upload Arquivos
    public void saveFileToDatabase(String fileName, String filePath) {
        ElementosArteModel elementosArteModel = new ElementosArteModel();
        elementosArteModel.setFileName(fileName);
        elementosArteModel.setPath(filePath);

        // Save the file information to the database
        elementosArteRepository.save(elementosArteModel);
    }

}
