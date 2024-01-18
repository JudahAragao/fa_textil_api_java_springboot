package com.fatextil.service;

import com.fatextil.model.PerfilAcessoModel;
import com.fatextil.repository.PerfilAcessoRepository;
import com.fatextil.rest.dto.PerfilAcessoDto;
import com.fatextil.rest.form.PerfilAcessoForm;
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
public class PerfilAcessoService {

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    // Busca completa no banco de dados
    public List<PerfilAcessoDto> findAll() {
        List<PerfilAcessoModel> perfilAcessoList = perfilAcessoRepository.findAll();
        return convertListToDto(perfilAcessoList);
    }

    // Busca no banco de dados
    public PerfilAcessoDto findById(Long perfilAcessoId) {
        try {
            PerfilAcessoModel perfilAcessoModel = perfilAcessoRepository.findById(perfilAcessoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + perfilAcessoId + " Tipo: " + PerfilAcessoModel.class.getName()));
            return convertModelToDto(perfilAcessoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + perfilAcessoId + " Tipo: " + PerfilAcessoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public PerfilAcessoDto insert(PerfilAcessoForm perfilAcessoForm) {
        try {
            PerfilAcessoModel perfilAcessoNovo = convertFormToModel(perfilAcessoForm);

            Optional<PerfilAcessoModel> byNomePerfilAcesso = perfilAcessoRepository.findByNomePerfilAcesso(perfilAcessoNovo.getNomePerfilAcesso());

            if (byNomePerfilAcesso.isPresent())
                throw new IllegalStateException("Nome do Perfil de Acesso já registrada.");

            perfilAcessoNovo = perfilAcessoRepository.save(perfilAcessoNovo);
            return convertModelToDto(perfilAcessoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public PerfilAcessoDto update(PerfilAcessoForm perfilAcessoForm, Long perfilAcessoId) {
        try {
            Optional<PerfilAcessoModel> perfilAcessoExistente = perfilAcessoRepository.findById(perfilAcessoId);
            if (perfilAcessoExistente.isPresent()) {
                PerfilAcessoModel perfilAcessoAtualizado = perfilAcessoExistente.get();
                perfilAcessoAtualizado.setNomePerfilAcesso(perfilAcessoForm.getNomePerfilAcesso());
                perfilAcessoRepository.save(perfilAcessoAtualizado);
                return convertModelToDto(perfilAcessoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do perfil de acesso não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do perfil de acesso não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long perfilAcessoId) {
        try {
            if (perfilAcessoRepository.existsById(perfilAcessoId)) {
                perfilAcessoRepository.deleteById(perfilAcessoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o perfil de acesso!");
        }
    }

    // Conversores de FORM para MODEL
    private PerfilAcessoModel convertFormToModel(PerfilAcessoForm perfilAcessoForm) {
        PerfilAcessoModel perfilAcessoModel = new PerfilAcessoModel();
        perfilAcessoModel.setNomePerfilAcesso(perfilAcessoForm.getNomePerfilAcesso());
        return perfilAcessoModel;
    }

    // Conversores de MODEL para DTO
    private PerfilAcessoDto convertModelToDto(PerfilAcessoModel perfilAcessoModel) {
        PerfilAcessoDto perfilAcessoDto = new PerfilAcessoDto();
        perfilAcessoDto.setId(perfilAcessoModel.getPerfilAcessoId());
        perfilAcessoDto.setNomePerfilAcesso(perfilAcessoModel.getNomePerfilAcesso());
        return perfilAcessoDto;
    }

    // Conversor de LIST para DTO
    private List<PerfilAcessoDto> convertListToDto(List<PerfilAcessoModel> perfilAcessoList) {
        List<PerfilAcessoDto> perfilAcessoDtoList = new ArrayList<>();
        for (PerfilAcessoModel perfilAcessoModel : perfilAcessoList) {
            PerfilAcessoDto perfilAcessoDto = convertModelToDto(perfilAcessoModel);
            perfilAcessoDtoList.add(perfilAcessoDto);
        }
        return perfilAcessoDtoList;
    }

}
