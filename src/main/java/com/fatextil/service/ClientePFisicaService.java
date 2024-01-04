package com.fatextil.service;

import com.fatextil.model.ClienteModel;
import com.fatextil.model.ClientePFisicaModel;
import com.fatextil.repository.ClientePFisicaRepository;
import com.fatextil.repository.ClienteRepository;
import com.fatextil.rest.dto.ClientePFisicaDto;
import com.fatextil.rest.form.ClientePFisicaForm;
import com.fatextil.rest.form.ClientePFisicaUpdateForm;
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
public class ClientePFisicaService {

    @Autowired
    private ClientePFisicaRepository clientePFisicaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    //Busca completa no banco de dados
    public List<ClientePFisicaDto> findAll(){
        List<ClientePFisicaModel> clientePFisicaList = clientePFisicaRepository.findAll();
        return convertListToDto(clientePFisicaList);
    }

    // Busca no banco de dados
    public ClientePFisicaDto findById(Long clientPFisicaId) {
        try {
            ClientePFisicaModel clientePFisicaModel = clientePFisicaRepository.findById(clientPFisicaId).get();
            return convertModelToDto(clientePFisicaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + clientPFisicaId + "Tipo: " + ClientePFisicaModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public ClientePFisicaDto insert(ClientePFisicaForm clientePFisicaForm) {
        try {
            ClientePFisicaModel clientePFisicaNovo = convertFormToModel(clientePFisicaForm);
            Optional<ClientePFisicaModel> byNome = clientePFisicaRepository.findByNome(clientePFisicaNovo.getNome());
            Optional<ClientePFisicaModel> byTelefone = clientePFisicaRepository.findByTelefone(clientePFisicaNovo.getTelefone());
            Optional<ClientePFisicaModel> byEmail = clientePFisicaRepository.findByEmail(clientePFisicaNovo.getEmail());
            Optional<ClientePFisicaModel> byCpf = clientePFisicaRepository.findByCpf(clientePFisicaNovo.getCpf());

            if (byNome.isPresent())
                throw new IllegalStateException("Nome já registrado.");
            if (byTelefone.isPresent())
                throw new IllegalStateException("Telefone já registrado.");
            if (byEmail.isPresent())
                throw new IllegalStateException("E-mail já registrado.");
            if (byCpf.isPresent())
                throw new IllegalStateException("CPF já registrado.");


            clientePFisicaNovo = clientePFisicaRepository.save(clientePFisicaNovo);

            return convertModelToDto(clientePFisicaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public ClientePFisicaDto update(ClientePFisicaUpdateForm clientePFisicaUpdateForm, long clienteId) {
        try {
            Optional<ClientePFisicaModel> clientePfisicaExistente = clientePFisicaRepository.findById(clienteId);
            if (clientePfisicaExistente.isPresent()) {
                ClientePFisicaModel clientePFisicaAtualizado = clientePfisicaExistente.get();

                clientePFisicaAtualizado.setNome(clientePFisicaUpdateForm.getNome());
                clientePFisicaAtualizado.setTelefone(clientePFisicaUpdateForm.getTelefone());
                clientePFisicaAtualizado.setEmail(clientePFisicaUpdateForm.getEmail());
                clientePFisicaAtualizado.setLogradouro(clientePFisicaUpdateForm.getLogradouro());
                clientePFisicaAtualizado.setNumeroImovel(clientePFisicaUpdateForm.getNumeroImovel());
                clientePFisicaAtualizado.setBairro(clientePFisicaUpdateForm.getBairro());
                clientePFisicaAtualizado.setComplemento(clientePFisicaUpdateForm.getComplemento());
                clientePFisicaAtualizado.setCep(clientePFisicaUpdateForm.getCep());
                clientePFisicaAtualizado.setAtivo(clientePFisicaUpdateForm.getAtivo());

                clientePFisicaRepository.save(clientePFisicaAtualizado);
                return convertModelToDto(clientePFisicaAtualizado);
            } else {
                throw new DataIntegrityException("O id do cliente não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchidos(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(long clientePFisicaId) {
        try {
            if (clientePFisicaRepository.existsById(clientePFisicaId)) {
                clientePFisicaRepository.deleteById(clientePFisicaId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente!");
        }
    }

    // Conversores de FORM para MODEL
    private ClientePFisicaModel convertFormToModel(ClientePFisicaForm clientePFisicaForm){
        ClientePFisicaModel clientePFisicaModel = new ClientePFisicaModel();

        ClienteModel cliente = clienteRepository.findById(clientePFisicaForm.getClienteId())
                .orElseThrow(() -> new RuntimeException("Terceirizada não encontrada"));
        clientePFisicaModel.setClienteId(cliente);

        clientePFisicaModel.setNome(clientePFisicaForm.getNome());
        clientePFisicaModel.setTelefone(clientePFisicaForm.getTelefone());
        clientePFisicaModel.setEmail(clientePFisicaForm.getEmail());
        clientePFisicaModel.setLogradouro(clientePFisicaForm.getLogradouro());
        clientePFisicaModel.setNumeroImovel(clientePFisicaForm.getNumeroImovel());
        clientePFisicaModel.setBairro(clientePFisicaForm.getBairro());
        clientePFisicaModel.setComplemento(clientePFisicaForm.getComplemento());
        clientePFisicaModel.setCep(clientePFisicaForm.getCep());
        clientePFisicaModel.setCpf(clientePFisicaForm.getCpf());
        clientePFisicaModel.setAtivo(clientePFisicaForm.getAtivo());
        clientePFisicaModel.setDataCadastro(clientePFisicaForm.getDataCadastro());

        return clientePFisicaModel;
    }

    // Conversores de MODEL para DTO
    private ClientePFisicaDto convertModelToDto(ClientePFisicaModel clientePFisicaModel) {
        ClientePFisicaDto clientePFisicaDto = new ClientePFisicaDto();
        clientePFisicaDto.setClientePFisicaId(clientePFisicaModel.getClientePFisicaId());
        clientePFisicaDto.setClienteId(clientePFisicaModel.getClienteId().getClienteId());
        clientePFisicaDto.setNome(clientePFisicaModel.getNome());
        clientePFisicaDto.setTelefone(clientePFisicaModel.getTelefone());
        clientePFisicaDto.setEmail(clientePFisicaModel.getEmail());
        clientePFisicaDto.setLogradouro(clientePFisicaModel.getLogradouro());
        clientePFisicaDto.setNumeroImovel(clientePFisicaModel.getNumeroImovel());
        clientePFisicaDto.setBairro(clientePFisicaModel.getBairro());
        clientePFisicaDto.setComplemento(clientePFisicaModel.getComplemento());
        clientePFisicaDto.setCep(clientePFisicaModel.getCep());
        clientePFisicaDto.setCpf(clientePFisicaModel.getCpf());
        clientePFisicaDto.setAtivo(clientePFisicaModel.getAtivo());
        clientePFisicaDto.setDataCadastro(clientePFisicaModel.getDataCadastro());

        return clientePFisicaDto;
    }

    // Convsersor de LIST para DTO
    private List<ClientePFisicaDto> convertListToDto(List<ClientePFisicaModel> list) {
        List<ClientePFisicaDto> clientePFisicaDtoList = new ArrayList<>();
        for(ClientePFisicaModel clientePFisicamodel : list) {
            ClientePFisicaDto clienteDto = this.convertModelToDto(clientePFisicamodel);
            clientePFisicaDtoList.add(clienteDto);
        }
        return clientePFisicaDtoList;
    }
}
