package com.fatextil.service;

import com.fatextil.model.StatusPedidoModel;
import com.fatextil.repository.StatusPedidoRepository;
import com.fatextil.rest.dto.StatusPedidoDto;
import com.fatextil.rest.form.StatusPedidoForm;
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
public class StatusPedidoService {

    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    public List<StatusPedidoDto> findAll() {
        List<StatusPedidoModel> statusPedidoList = statusPedidoRepository.findAll();
        return convertListToDto(statusPedidoList);
    }

    public StatusPedidoDto findById(Long statusPedidoId) {
        try {
            StatusPedidoModel statusPedidoModel = statusPedidoRepository.findById(statusPedidoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + statusPedidoId + " Tipo: " + StatusPedidoModel.class.getName()));

            return convertModelToDto(statusPedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + statusPedidoId + " Tipo: " + StatusPedidoModel.class.getName());
        }
    }

    public StatusPedidoDto insert(StatusPedidoForm statusPedidoForm) {
        try {
            StatusPedidoModel statusPedidoNovo = convertFormToModel(statusPedidoForm);
            Optional<StatusPedidoModel> byNomeStatus = statusPedidoRepository.findByNomeStatusPedido(statusPedidoNovo.getNomeStatusPedido());

            if (byNomeStatus.isPresent())
                throw new IllegalStateException("Nome de status já registrado.");

            statusPedidoNovo = statusPedidoRepository.save(statusPedidoNovo);
            return convertModelToDto(statusPedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public StatusPedidoDto update(StatusPedidoForm statusPedidoForm, Long statusPedidoId) {
        try {
            Optional<StatusPedidoModel> statusPedidoExistente = statusPedidoRepository.findById(statusPedidoId);
            if (statusPedidoExistente.isPresent()) {
                StatusPedidoModel statusPedidoAtualizado = statusPedidoExistente.get();

                statusPedidoAtualizado.setNomeStatusPedido(statusPedidoForm.getNomeStatusPedido());

                statusPedidoRepository.save(statusPedidoAtualizado);
                return convertModelToDto(statusPedidoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do status de pedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do status de pedido não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long statusPedidoId) {
        try {
            if (statusPedidoRepository.existsById(statusPedidoId)) {
                statusPedidoRepository.deleteById(statusPedidoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um status de pedido!");
        }
    }

    private StatusPedidoModel convertFormToModel(StatusPedidoForm statusPedidoForm) {
        StatusPedidoModel statusPedidoModel = new StatusPedidoModel();
        statusPedidoModel.setNomeStatusPedido(statusPedidoForm.getNomeStatusPedido());
        return statusPedidoModel;
    }

    private StatusPedidoDto convertModelToDto(StatusPedidoModel statusPedidoModel) {
        StatusPedidoDto statusPedidoDto = new StatusPedidoDto();
        statusPedidoDto.setStatusPedidoId(statusPedidoModel.getStatusPedidoId());
        statusPedidoDto.setNomeStatusPedido(statusPedidoModel.getNomeStatusPedido());
        return statusPedidoDto;
    }

    private List<StatusPedidoDto> convertListToDto(List<StatusPedidoModel> statusPedidoList) {
        List<StatusPedidoDto> statusPedidoDtoList = new ArrayList<>();
        for (StatusPedidoModel statusPedidoModel : statusPedidoList) {
            StatusPedidoDto statusPedidoDto = convertModelToDto(statusPedidoModel);
            statusPedidoDtoList.add(statusPedidoDto);
        }
        return statusPedidoDtoList;
    }

}
