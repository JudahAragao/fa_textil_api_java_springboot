package com.fatextil.service;

import com.fatextil.model.ClienteModel;
import com.fatextil.model.PedidoModel;
import com.fatextil.model.StatusPedidoModel;
import com.fatextil.repository.ClienteRepository;
import com.fatextil.repository.PedidoRepository;
import com.fatextil.repository.StatusPedidoRepository;
import com.fatextil.rest.dto.PedidoDto;
import com.fatextil.rest.form.PedidoForm;
import com.fatextil.rest.form.PedidoUpdateForm;
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
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    // Busca completa no banco de dados
    public List<PedidoDto> findAll() {
        List<PedidoModel> pedidoList = pedidoRepository.findAll();
        return convertListToDto(pedidoList);
    }

    // Busca no banco de dados
    public PedidoDto findById(Long pedidoId) {
        try {
            PedidoModel pedidoModel = pedidoRepository.findById(pedidoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + pedidoId + " Tipo: " + PedidoModel.class.getName()));
            return convertModelToDto(pedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + pedidoId + " Tipo: " + PedidoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public PedidoDto insert(PedidoForm pedidoForm) {
        try {
            PedidoModel pedidoNovo = convertFormToModel(pedidoForm);
            pedidoNovo = pedidoRepository.save(pedidoNovo);
            return convertModelToDto(pedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public PedidoDto update(PedidoUpdateForm pedidoUpdateForm, Long pedidoId) {
        try {
            Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(pedidoId);
            if (pedidoExistente.isPresent()) {
                PedidoModel pedidoAtualizado = pedidoExistente.get();
                pedidoAtualizado.setDescricao(pedidoUpdateForm.getDescricao());
                pedidoAtualizado.setValorPedido(pedidoUpdateForm.getValor());
                pedidoRepository.save(pedidoAtualizado);
                return convertModelToDto(pedidoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do pedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do pedido não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long pedidoId) {
        try {
            if (pedidoRepository.existsById(pedidoId)) {
                pedidoRepository.deleteById(pedidoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um pedido!");
        }
    }

    // Conversores de FORM para MODEL
    private PedidoModel convertFormToModel(PedidoForm pedidoForm) {
        PedidoModel pedidoModel = new PedidoModel();

        // Defina os relacionamentos para ClienteModel e StatusPedidoModel
        ClienteModel cliente = clienteRepository.findById(pedidoForm.getClienteId())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        pedidoModel.setClienteId(cliente);

        StatusPedidoModel statusPedido = statusPedidoRepository.findById(pedidoForm.getClienteId())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        pedidoModel.setStatusPedidoId(statusPedido);

        pedidoModel.setDescricao(pedidoForm.getDescricao());
        pedidoModel.setDataPedido(pedidoForm.getDataPedido());
        pedidoModel.setHoraPedido(pedidoForm.getHoraPedido());
        pedidoModel.setValorPedido(pedidoForm.getValor());

        return pedidoModel;
    }

    // Conversores de MODEL para DTO
    private PedidoDto convertModelToDto(PedidoModel pedidoModel) {
        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId(pedidoModel.getCodPedido());
        pedidoDto.setClienteId(pedidoModel.getClienteId().getClienteId());
        pedidoDto.setStatusPedidoId(pedidoModel.getStatusPedidoId().getStatusPedidoId());
        pedidoDto.setDescricao(pedidoModel.getDescricao());
        pedidoDto.setDataPedido(pedidoModel.getDataPedido());
        pedidoDto.setHoraPedido(pedidoModel.getHoraPedido());
        pedidoDto.setValor(pedidoModel.getValorPedido());

        return pedidoDto;
    }

    // Conversor de LIST para DTO
    private List<PedidoDto> convertListToDto(List<PedidoModel> pedidoList) {
        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        for (PedidoModel pedidoModel : pedidoList) {
            PedidoDto pedidoDto = convertModelToDto(pedidoModel);
            pedidoDtoList.add(pedidoDto);
        }
        return pedidoDtoList;
    }

}
