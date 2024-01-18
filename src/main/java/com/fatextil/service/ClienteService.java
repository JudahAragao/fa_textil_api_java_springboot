package com.fatextil.service;

import com.fatextil.model.ClienteModel;
import com.fatextil.repository.ClienteRepository;
import com.fatextil.rest.dto.ClienteDto;
import com.fatextil.rest.form.ClienteForm;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Busca completa no banco de dados
    public List<ClienteDto> findAll(){
        List<ClienteModel> clienteList = clienteRepository.findAll();
        return convertListToDto(clienteList);
    }

    // Busca no banco de dados
    public ClienteDto findById(Long clientId) {
        try {
            ClienteModel clienteModel = clienteRepository.findById(clientId).get();
            return convertClienteModelToClienteDto (clienteModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + clientId + "Tipo: " + ClienteModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public ClienteDto insert(ClienteForm clienteForm) {
        try {
            ClienteModel clienteNovo = convertClienteFormToClienteModel(clienteForm);
            Optional<ClienteModel> byTipoCliente = clienteRepository.findByTipoCliente(clienteNovo.getTipoCliente());

            if (byTipoCliente.isPresent()) {
                throw new IllegalStateException("Tipo de cliente já registrado.");
            }

            clienteNovo = clienteRepository.save(clienteNovo);

            return convertClienteModelToClienteDto(clienteNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public ClienteDto update(ClienteForm clienteUpdateForm, long clienteId) {
        try {
            Optional<ClienteModel> clienteExistente = clienteRepository.findById(clienteId);
            if (clienteExistente.isPresent()) {
                ClienteModel clienteAtualizado = clienteExistente.get();
                clienteAtualizado.setTipoCliente(clienteUpdateForm.getTipoCliente());

                clienteRepository.save(clienteAtualizado);
                return convertClienteModelToClienteDto(clienteAtualizado);
            } else {
                throw new DataIntegrityException("O id do cliente não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchidos(s).");
        }

    }

    // Exclusão no banco de dados
    public void delete(long clienteId) {
        try {
            if (clienteRepository.existsById(clienteId)) {
                clienteRepository.deleteById(clienteId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente!");
        }
    }

    // Conversores de FORM para MODEL
    private ClienteModel convertClienteFormToClienteModel(ClienteForm clienteForm){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setTipoCliente(clienteForm.getTipoCliente());

        return clienteModel;
    }


    // Conversores de MODEL para DTO
    private ClienteDto convertClienteModelToClienteDto(ClienteModel clienteModel) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteModel.getClienteId());
        clienteDto.setTipoCliente(clienteModel.getTipoCliente());

        return clienteDto;
    }

    // Convsersor de LIST para DTO
    private List<ClienteDto> convertListToDto(List<ClienteModel> list) {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        for(ClienteModel clienteModel : list) {
            ClienteDto clienteDto = this.convertClienteModelToClienteDto(clienteModel);
            clienteDtoList.add(clienteDto);
        }
        return clienteDtoList;
    }

}
