package com.fatextil.service;

import com.fatextil.model.CategoriaElementoArteModel;
import com.fatextil.model.ClientePFisicaModel;
import com.fatextil.model.DemandaPProdutoModel;
import com.fatextil.repository.CategoriaElementoArteRepository;
import com.fatextil.rest.dto.CategoriaElementoArteDto;
import com.fatextil.rest.dto.ClientePFisicaDto;
import com.fatextil.rest.form.CategoriaElementoArteForm;
import com.fatextil.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoriaElementoArteService {

    @Autowired
    private CategoriaElementoArteRepository categoriaElementoArteRepository;

    //Busca completa no banco de dados
    public List<CategoriaElementoArteDto> findAll() {
        List<CategoriaElementoArteModel> categoriaElementoArteList = categoriaElementoArteRepository.findAll();
        return convertListToDto(categoriaElementoArteList);
    }

    // Busca no banco de dados
    public CategoriaElementoArteDto findById(Long categoriaElementoArteId) {
        try {
            CategoriaElementoArteModel categoriaElementoArteModel = categoriaElementoArteRepository.findById(categoriaElementoArteId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + categoriaElementoArteId + " Tipo: " + CategoriaElementoArteModel.class.getName()));

            return convertCategoriaModelToDto(categoriaElementoArteModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + categoriaElementoArteId + "Tipo: " + ClientePFisicaModel.class.getName());
        }
    }

    public CategoriaElementoArteDto insert(CategoriaElementoArteForm categoriaElementoArteForm) {
        CategoriaElementoArteModel categoriaElementoArteModel = convertCategoriaFormToModel(categoriaElementoArteForm);
        categoriaElementoArteModel = categoriaElementoArteRepository.save(categoriaElementoArteModel);
        return convertCategoriaModelToDto(categoriaElementoArteModel);
    }

    public CategoriaElementoArteDto update(CategoriaElementoArteForm categoriaElementoArteForm, Long categoriaElementoId) {
        Optional<CategoriaElementoArteModel> categoriaElementoArteModelOptional = categoriaElementoArteRepository.findById(categoriaElementoId);
        if (categoriaElementoArteModelOptional.isPresent()) {
            CategoriaElementoArteModel categoriaElementoArteModel = categoriaElementoArteModelOptional.get();
            categoriaElementoArteModel.setNomeCategoria(categoriaElementoArteForm.getNomeCategoria());
            categoriaElementoArteModel = categoriaElementoArteRepository.save(categoriaElementoArteModel);
            return convertCategoriaModelToDto(categoriaElementoArteModel);
        } else {
            // Lançar exceção ou retornar um valor padrão, dependendo dos requisitos do sistema.
            return null;
        }
    }

    public void delete(Long categoriaElementoId) {
        if (categoriaElementoArteRepository.existsById(categoriaElementoId)) {
            categoriaElementoArteRepository.deleteById(categoriaElementoId);
        } else {
            // Lançar exceção ou tratar de acordo com os requisitos do sistema.
        }
    }

    // Conversores de FORM para MODEL
    public CategoriaElementoArteModel convertCategoriaFormToModel(CategoriaElementoArteForm categoriaElementoArteForm) {
        CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
        categoriaElementoArteModel.setNomeCategoria(categoriaElementoArteForm.getNomeCategoria());
        return categoriaElementoArteModel;
    }

    // Conversores de MODEL para DTO
    public CategoriaElementoArteDto convertCategoriaModelToDto(CategoriaElementoArteModel categoriaElementoArteModel) {
        CategoriaElementoArteDto categoriaElementoArteDto = new CategoriaElementoArteDto();
        categoriaElementoArteDto.setCategoriaElementoId(categoriaElementoArteModel.getCategoriaElementoId());
        categoriaElementoArteDto.setNomeCategoria(categoriaElementoArteModel.getNomeCategoria());
        return categoriaElementoArteDto;
    }

    // Convsersor de LIST para DTO
    public List<CategoriaElementoArteDto> convertListToDto(List<CategoriaElementoArteModel> categoriaElementoArteList) {
        List<CategoriaElementoArteDto> categoriaElementoArteDtoList = new ArrayList<>();
        for (CategoriaElementoArteModel categoriaElementoArteModel : categoriaElementoArteList) {
            CategoriaElementoArteDto categoriaElementoArteDto = convertCategoriaModelToDto(categoriaElementoArteModel);
            categoriaElementoArteDtoList.add(categoriaElementoArteDto);
        }
        return categoriaElementoArteDtoList;
    }

}
