package com.fatextil.service;

import com.fatextil.model.StatusFabricacaoModel;
import com.fatextil.repository.StatusFabricacaoRepository;
import com.fatextil.rest.dto.StatusFabricacaoDto;
import com.fatextil.rest.form.StatusFabricacaoForm;
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
public class StatusFabricacaoService {

    @Autowired
    private StatusFabricacaoRepository statusFabricacaoRepository;

    public List<StatusFabricacaoDto> findAll() {
        List<StatusFabricacaoModel> statusFabricacaoList = statusFabricacaoRepository.findAll();
        return convertListToDto(statusFabricacaoList);
    }

    public StatusFabricacaoDto findById(Long statusFabricacaoId) {
        try {
            StatusFabricacaoModel statusFabricacaoModel = statusFabricacaoRepository.findById(statusFabricacaoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + statusFabricacaoId + " Tipo: " + StatusFabricacaoModel.class.getName()));

            return convertModelToDto(statusFabricacaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + statusFabricacaoId + " Tipo: " + StatusFabricacaoModel.class.getName());
        }
    }

    public StatusFabricacaoDto insert(StatusFabricacaoForm statusFabricacaoForm) {
        try {
            StatusFabricacaoModel statusFabricacaoNovo = convertFormToModel(statusFabricacaoForm);
            Optional<StatusFabricacaoModel> byNomeStatus = statusFabricacaoRepository.findByNomeStatusFabricacao(statusFabricacaoNovo.getNomeStatusFabricacao());

            if (byNomeStatus.isPresent())
                throw new IllegalStateException("Nome de status já registrado.");

            statusFabricacaoNovo = statusFabricacaoRepository.save(statusFabricacaoNovo);
            return convertModelToDto(statusFabricacaoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public StatusFabricacaoDto update(StatusFabricacaoForm statusFabricacaoForm, Long statusFabricacaoId) {
        try {
            Optional<StatusFabricacaoModel> statusFabricacaoExistente = statusFabricacaoRepository.findById(statusFabricacaoId);
            if (statusFabricacaoExistente.isPresent()) {
                StatusFabricacaoModel statusFabricacaoAtualizado = statusFabricacaoExistente.get();

                statusFabricacaoAtualizado.setNomeStatusFabricacao(statusFabricacaoForm.getNomeStatusFabricacao());

                statusFabricacaoRepository.save(statusFabricacaoAtualizado);
                return convertModelToDto(statusFabricacaoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do status de fabricação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do status de fabricação não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long statusFabricacaoId) {
        try {
            if (statusFabricacaoRepository.existsById(statusFabricacaoId)) {
                statusFabricacaoRepository.deleteById(statusFabricacaoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um status de fabricação!");
        }
    }

    private StatusFabricacaoModel convertFormToModel(StatusFabricacaoForm statusFabricacaoForm) {
        StatusFabricacaoModel statusFabricacaoModel = new StatusFabricacaoModel();
        statusFabricacaoModel.setNomeStatusFabricacao(statusFabricacaoForm.getNomeStatusFabricacao());
        return statusFabricacaoModel;
    }

    private StatusFabricacaoDto convertModelToDto(StatusFabricacaoModel statusFabricacaoModel) {
        StatusFabricacaoDto statusFabricacaoDto = new StatusFabricacaoDto();
        statusFabricacaoDto.setStatusFabricacaoId(statusFabricacaoModel.getStatusFabricacaoId());
        statusFabricacaoDto.setNomeStatusFabricacao(statusFabricacaoModel.getNomeStatusFabricacao());
        return statusFabricacaoDto;
    }

    private List<StatusFabricacaoDto> convertListToDto(List<StatusFabricacaoModel> statusFabricacaoList) {
        List<StatusFabricacaoDto> statusFabricacaoDtoList = new ArrayList<>();
        for (StatusFabricacaoModel statusFabricacaoModel : statusFabricacaoList) {
            StatusFabricacaoDto statusFabricacaoDto = convertModelToDto(statusFabricacaoModel);
            statusFabricacaoDtoList.add(statusFabricacaoDto);
        }
        return statusFabricacaoDtoList;
    }

}
