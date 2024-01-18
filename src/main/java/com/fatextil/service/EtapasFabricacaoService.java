package com.fatextil.service;

import com.fatextil.model.EtapasFabricacaoModel;
import com.fatextil.repository.EtapasFabricacaoRepository;
import com.fatextil.rest.dto.EtapasFabricacaoDto;
import com.fatextil.rest.form.EtapasFabricacaoForm;
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
public class EtapasFabricacaoService {

    @Autowired
    private EtapasFabricacaoRepository etapasFabricacaoRepository;

    // Busca completa no banco de dados
    public List<EtapasFabricacaoDto> findAll() {
        List<EtapasFabricacaoModel> etapasFabricacaoList = etapasFabricacaoRepository.findAll();
        return convertListToDto(etapasFabricacaoList);
    }

    // Busca no banco de dados
    public EtapasFabricacaoDto findById(Long etapaFabricacaoId) {
        try {
            EtapasFabricacaoModel etapasFabricacaoModel = etapasFabricacaoRepository.findById(etapaFabricacaoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + etapaFabricacaoId + " Tipo: " + EtapasFabricacaoModel.class.getName()));

            return convertModelToDto(etapasFabricacaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + etapaFabricacaoId + " Tipo: " + EtapasFabricacaoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public EtapasFabricacaoDto insert(EtapasFabricacaoForm etapasFabricacaoForm) {
        try {
            EtapasFabricacaoModel etapasFabricacaoNovo = convertFormToModel(etapasFabricacaoForm);
            Optional<EtapasFabricacaoModel> byNomeEtapa = etapasFabricacaoRepository.findByNomeEtapa(etapasFabricacaoNovo.getNomeEtapa());

            if (byNomeEtapa.isPresent())
                throw new IllegalStateException("Nome da etapa já registrado.");

            etapasFabricacaoNovo = etapasFabricacaoRepository.save(etapasFabricacaoNovo);
            return convertModelToDto(etapasFabricacaoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public EtapasFabricacaoDto update(EtapasFabricacaoForm etapasFabricacaoForm, Long etapaFabricacaoId) {
        try {
            Optional<EtapasFabricacaoModel> etapasFabricacaoExistente = etapasFabricacaoRepository.findById(etapaFabricacaoId);
            if (etapasFabricacaoExistente.isPresent()) {
                EtapasFabricacaoModel etapasFabricacaoAtualizado = etapasFabricacaoExistente.get();

                etapasFabricacaoAtualizado.setNomeEtapa(etapasFabricacaoForm.getNomeEtapa());

                etapasFabricacaoRepository.save(etapasFabricacaoAtualizado);
                return convertModelToDto(etapasFabricacaoAtualizado);
            } else {
                throw new DataIntegrityException("O ID da etapa de fabricação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da EtapasFabricacao não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long etapaFabricacaoId) {
        try {
            if (etapasFabricacaoRepository.existsById(etapaFabricacaoId)) {
                etapasFabricacaoRepository.deleteById(etapaFabricacaoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Etapa de Fabricação!");
        }
    }

    // Conversores de FORM para MODEL
    public EtapasFabricacaoModel convertFormToModel(EtapasFabricacaoForm etapasFabricacaoForm) {
        EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
        etapasFabricacaoModel.setNomeEtapa(etapasFabricacaoForm.getNomeEtapa());
        return etapasFabricacaoModel;
    }

    // Conversores de MODEL para DTO
    public EtapasFabricacaoDto convertModelToDto(EtapasFabricacaoModel etapasFabricacaoModel) {
        EtapasFabricacaoDto etapasFabricacaoDto = new EtapasFabricacaoDto();
        etapasFabricacaoDto.setId(etapasFabricacaoModel.getEtapasFabricacaoId());
        etapasFabricacaoDto.setNomeEtapa(etapasFabricacaoModel.getNomeEtapa());
        return etapasFabricacaoDto;
    }

    // Conversor de LIST para DTO
    public List<EtapasFabricacaoDto> convertListToDto(List<EtapasFabricacaoModel> etapasFabricacaoList) {
        List<EtapasFabricacaoDto> etapasFabricacaoDtoList = new ArrayList<>();
        for (EtapasFabricacaoModel etapasFabricacaoModel : etapasFabricacaoList) {
            EtapasFabricacaoDto etapasFabricacaoDto = convertModelToDto(etapasFabricacaoModel);
            etapasFabricacaoDtoList.add(etapasFabricacaoDto);
        }
        return etapasFabricacaoDtoList;
    }

}
