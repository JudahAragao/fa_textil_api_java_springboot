package com.fatextil.service;

import com.fatextil.model.*;
import com.fatextil.repository.FabricacaoPedidoRepository;
import com.fatextil.rest.dto.FabricacaoPedidoDto;
import com.fatextil.rest.form.FabricacaoPedidoForm;
import com.fatextil.service.exceptions.DataIntegrityException;
import com.fatextil.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FabricacaoPedidoService {

    @Autowired
    private FabricacaoPedidoRepository fabricacaoPedidoRepository;

    // Busca completa no banco de dados
    public List<FabricacaoPedidoDto> findAll() {
        List<FabricacaoPedidoModel> fabricacaoPedidoList = fabricacaoPedidoRepository.findAll();
        return convertListToDto(fabricacaoPedidoList);
    }

    // Busca no banco de dados
    public FabricacaoPedidoDto findById(Long fabricacaoPedidoId) {
        try {
            FabricacaoPedidoModel fabricacaoPedidoModel = fabricacaoPedidoRepository.findById(fabricacaoPedidoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + fabricacaoPedidoId + " Tipo: " + FabricacaoPedidoModel.class.getName()));

            return convertModelToDto(fabricacaoPedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + fabricacaoPedidoId + " Tipo: " + FabricacaoPedidoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public FabricacaoPedidoDto insert(FabricacaoPedidoForm fabricacaoPedidoForm) {
        try {
            FabricacaoPedidoModel fabricacaoPedidoNovo = convertFormToModel(fabricacaoPedidoForm);

            fabricacaoPedidoNovo = fabricacaoPedidoRepository.save(fabricacaoPedidoNovo);
            return convertModelToDto(fabricacaoPedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public FabricacaoPedidoDto update(FabricacaoPedidoForm fabricacaoPedidoForm, Long fabricacaoPedidoId) {
        try {
            Optional<FabricacaoPedidoModel> fabricacaoPedidoExistente = fabricacaoPedidoRepository.findById(fabricacaoPedidoId);
            if (fabricacaoPedidoExistente.isPresent()) {
                FabricacaoPedidoModel fabricacaoPedidoAtualizado = fabricacaoPedidoExistente.get();

                fabricacaoPedidoAtualizado.setDataInicio(fabricacaoPedidoForm.getDataInicio());
                fabricacaoPedidoAtualizado.setDataPrevisao(fabricacaoPedidoForm.getDataPrevisao());
                fabricacaoPedidoAtualizado.setDataFim(fabricacaoPedidoForm.getDataFim());

                fabricacaoPedidoRepository.save(fabricacaoPedidoAtualizado);
                return convertModelToDto(fabricacaoPedidoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do FabricacaoPedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do FabricacaoPedido não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long fabricacaoPedidoId) {
        try {
            if (fabricacaoPedidoRepository.existsById(fabricacaoPedidoId)) {
                fabricacaoPedidoRepository.deleteById(fabricacaoPedidoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um FabricacaoPedido!");
        }
    }

    // Conversores de FORM para MODEL
    public FabricacaoPedidoModel convertFormToModel(FabricacaoPedidoForm fabricacaoPedidoForm) {
        FabricacaoPedidoModel fabricacaoPedidoModel = new FabricacaoPedidoModel();

        fabricacaoPedidoModel.setEtapasFabricacaoId(fabricacaoPedidoForm.getEtapasFabricacaoId());
        fabricacaoPedidoModel.setItensPedidoId(fabricacaoPedidoForm.getItensPedidoId());
        fabricacaoPedidoModel.setFuncionarioId(fabricacaoPedidoForm.getFuncionarioId());
        fabricacaoPedidoModel.setStatusFabricacaoId(fabricacaoPedidoForm.getStatusFabricacaoId());
        fabricacaoPedidoModel.setDataInicio(fabricacaoPedidoForm.getDataInicio());
        fabricacaoPedidoModel.setDataPrevisao(fabricacaoPedidoForm.getDataPrevisao());
        fabricacaoPedidoModel.setDataFim(fabricacaoPedidoForm.getDataFim());

        return fabricacaoPedidoModel;
    }

    // Conversores de MODEL para DTO
    public FabricacaoPedidoDto convertModelToDto(FabricacaoPedidoModel fabricacaoPedidoModel) {
        FabricacaoPedidoDto fabricacaoPedidoDto = new FabricacaoPedidoDto();

        fabricacaoPedidoDto.setFabricacaoPedidoId(fabricacaoPedidoModel.getFabricacaoPedidoId());
        fabricacaoPedidoDto.setEtapasFabricacaoId(fabricacaoPedidoModel.getEtapasFabricacaoId());
        fabricacaoPedidoDto.setItensPedidoId(fabricacaoPedidoModel.getItensPedidoId());
        fabricacaoPedidoDto.setFuncionarioId(fabricacaoPedidoModel.getFuncionarioId());
        fabricacaoPedidoDto.setStatusFabricacaoId(fabricacaoPedidoModel.getStatusFabricacaoId());
        fabricacaoPedidoDto.setDataInicio(fabricacaoPedidoModel.getDataInicio());
        fabricacaoPedidoDto.setDataPrevisao(fabricacaoPedidoModel.getDataPrevisao());
        fabricacaoPedidoDto.setDataFim(fabricacaoPedidoModel.getDataFim());

        return fabricacaoPedidoDto;
    }

    // Conversor de LIST para DTO
    public List<FabricacaoPedidoDto> convertListToDto(List<FabricacaoPedidoModel> fabricacaoPedidoList) {
        List<FabricacaoPedidoDto> fabricacaoPedidoDtoList = new ArrayList<>();
        for (FabricacaoPedidoModel fabricacaoPedidoModel : fabricacaoPedidoList) {
            FabricacaoPedidoDto fabricacaoPedidoDto = convertModelToDto(fabricacaoPedidoModel);
            fabricacaoPedidoDtoList.add(fabricacaoPedidoDto);
        }
        return fabricacaoPedidoDtoList;
    }

}
