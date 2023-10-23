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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ElementosArteService {

    @Autowired
    private ElementosArteRepository elementosArteRepository;

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
            Optional<ElementosArteModel> byFileName = elementosArteRepository.findByFilename(elementosArteNovo.getFilename());

            if (byFileName.isPresent())
                throw new IllegalStateException("Arquivo já existe, por favor mude o nome do arquivo que esta tentando enviar!");

            elementosArteNovo = elementosArteRepository.save(elementosArteNovo);
            return convertModelToDto(elementosArteNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
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

                elementosArteAtualizado.setFilename(elementosArteForm.getFilename());
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

        elementosArteModel.setFilename(elementosArteForm.getFilename());
        elementosArteModel.setPath(elementosArteForm.getPath());

        return elementosArteModel;
    }

    // Conversores de MODEL para DTO
    public ElementosArteDto convertModelToDto(ElementosArteModel elementosArteModel) {
        ElementosArteDto elementosArteDto = new ElementosArteDto();

        elementosArteDto.setElementosArteId(elementosArteModel.getElementosArteId());
        elementosArteDto.setItensPedidoId(elementosArteModel.getItensPedidoId().getItensPedidoId());
        elementosArteDto.setCategoriaElementoId(elementosArteModel.getCategoriaElementoId().getCategoriaElementoId());
        elementosArteDto.setFilename(elementosArteModel.getFilename());
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

}
