package com.fatextil.service;

import com.fatextil.model.ClienteModel;
import com.fatextil.model.ClientePJuridicaModel;
import com.fatextil.repository.ClientePJuridicaRepository;
import com.fatextil.repository.ClienteRepository;
import com.fatextil.rest.dto.ClientePJuridicaDto;
import com.fatextil.rest.form.ClientePJuridicaForm;
import com.fatextil.rest.form.ClientePJuridicaUpdateForm;
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
public class ClientePJuridicaService {

    @Autowired
    private ClientePJuridicaRepository clientePJuridicaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    //Busca completa no banco de dados
    public List<ClientePJuridicaDto> findAll() {
        List<ClientePJuridicaModel> clientePJuridicaList = clientePJuridicaRepository.findAll();
        return convertListToDto(clientePJuridicaList);
    }

    // Busca no banco de dados
    public ClientePJuridicaDto findById(Long clientePJuridicaId) {
        try {
            ClientePJuridicaModel clientePJuridicaModel = clientePJuridicaRepository.findById(clientePJuridicaId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + clientePJuridicaId + " Tipo: " + ClientePJuridicaModel.class.getName()));

            return convertModelToDto(clientePJuridicaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + clientePJuridicaId + " Tipo: " + ClientePJuridicaModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public ClientePJuridicaDto insert(ClientePJuridicaForm clientePJuridicaForm) {
        try {
            ClientePJuridicaModel clientePJuridicaNovo = convertFormToModel(clientePJuridicaForm);

            Optional<ClientePJuridicaModel> byRazaoSocial = clientePJuridicaRepository.findByRazaoSocial(clientePJuridicaNovo.getRazaoSocial());
            Optional<ClientePJuridicaModel> byTelefone = clientePJuridicaRepository.findByTelefone(clientePJuridicaNovo.getTelefone());
            Optional<ClientePJuridicaModel> byEmail = clientePJuridicaRepository.findByEmail(clientePJuridicaNovo.getEmail());
            Optional<ClientePJuridicaModel> byInscricaoEstadual = clientePJuridicaRepository.findByInscricaoEstadual(clientePJuridicaNovo.getInscricaoEstadual());
            Optional<ClientePJuridicaModel> byInscricaoMunicipal = clientePJuridicaRepository.findByInscricaoMunicipal(clientePJuridicaNovo.getInscricaoMunicipal());
            Optional<ClientePJuridicaModel> byCnpj = clientePJuridicaRepository.findByCnpj(clientePJuridicaNovo.getCnpj());

            if (byRazaoSocial.isPresent())
                throw new IllegalStateException("Razão Social já registrada.");
            if (byTelefone.isPresent())
                throw new IllegalStateException("Telefone já registrado.");
            if (byEmail.isPresent())
                throw new IllegalStateException("Email já registrado.");
            if (byInscricaoEstadual.isPresent())
                throw new IllegalStateException("Inscrição Estadual já registrada.");
            if (byInscricaoMunicipal.isPresent())
                throw new IllegalStateException("Inscrição Municipal já registrada.");
            if (byCnpj.isPresent())
                throw new IllegalStateException("CNPJ já registrado.");

            clientePJuridicaNovo = clientePJuridicaRepository.save(clientePJuridicaNovo);
            return convertModelToDto(clientePJuridicaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public ClientePJuridicaDto update(ClientePJuridicaUpdateForm clientePJuridicaUpdateForm, Long clientePJuridicaId) {
        try {
            Optional<ClientePJuridicaModel> clientePJuridicaExistente = clientePJuridicaRepository.findById(clientePJuridicaId);
            if (clientePJuridicaExistente.isPresent()) {
                ClientePJuridicaModel clientePJuridicaAtualizado = clientePJuridicaExistente.get();

                clientePJuridicaAtualizado.setRepresentante(clientePJuridicaUpdateForm.getRepresentante());
                clientePJuridicaAtualizado.setTelefone(clientePJuridicaUpdateForm.getTelefone());
                clientePJuridicaAtualizado.setEmail(clientePJuridicaUpdateForm.getEmail());
                clientePJuridicaAtualizado.setSite(clientePJuridicaUpdateForm.getSite());
                clientePJuridicaAtualizado.setLogradouro(clientePJuridicaUpdateForm.getLogradouro());
                clientePJuridicaAtualizado.setNumeroImovel(clientePJuridicaUpdateForm.getNumeroImovel());
                clientePJuridicaAtualizado.setBairro(clientePJuridicaUpdateForm.getBairro());
                clientePJuridicaAtualizado.setComplemento(clientePJuridicaUpdateForm.getComplemento());
                clientePJuridicaAtualizado.setCep(clientePJuridicaUpdateForm.getCep());
                clientePJuridicaAtualizado.setAtivo(clientePJuridicaUpdateForm.getAtivo());

                clientePJuridicaRepository.save(clientePJuridicaAtualizado);
                return convertModelToDto(clientePJuridicaAtualizado);
            } else {
                throw new DataIntegrityException("O ID do cliente não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente PJ não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(long clientePJuridicaId) {
        try {
            if (clientePJuridicaRepository.existsById(clientePJuridicaId)) {
                clientePJuridicaRepository.deleteById(clientePJuridicaId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente!");
        }
    }

    // Conversores de FORM para MODEL
    public ClientePJuridicaModel convertFormToModel(ClientePJuridicaForm clientePJuridicaForm) {
        ClientePJuridicaModel clientePJuridicaModel = new ClientePJuridicaModel();

        ClienteModel cliente = clienteRepository.findById(clientePJuridicaForm.getClienteId())
                .orElseThrow(() -> new RuntimeException("Terceirizada não encontrada"));
        clientePJuridicaModel.setClienteId(cliente);

        clientePJuridicaModel.setRazaoSocial(clientePJuridicaForm.getRazaoSocial());
        clientePJuridicaModel.setRepresentante(clientePJuridicaForm.getRepresentante());
        clientePJuridicaModel.setTelefone(clientePJuridicaForm.getTelefone());
        clientePJuridicaModel.setEmail(clientePJuridicaForm.getEmail());
        clientePJuridicaModel.setSite(clientePJuridicaForm.getSite());
        clientePJuridicaModel.setLogradouro(clientePJuridicaForm.getLogradouro());
        clientePJuridicaModel.setNumeroImovel(clientePJuridicaForm.getNumeroImovel());
        clientePJuridicaModel.setBairro(clientePJuridicaForm.getBairro());
        clientePJuridicaModel.setComplemento(clientePJuridicaForm.getComplemento());
        clientePJuridicaModel.setCep(clientePJuridicaForm.getCep());
        clientePJuridicaModel.setInscricaoEstadual(clientePJuridicaForm.getInscricaoEstatual());
        clientePJuridicaModel.setInscricaoMunicipal(clientePJuridicaForm.getInscricaoMunicipal());
        clientePJuridicaModel.setCnpj(clientePJuridicaForm.getCnpj());
        clientePJuridicaModel.setAtivo(clientePJuridicaForm.getAtivo());
        clientePJuridicaModel.setDataCadastro(clientePJuridicaForm.getDataCadastro());

        return clientePJuridicaModel;
    }

    // Conversores de MODEL para DTO
    public ClientePJuridicaDto convertModelToDto(ClientePJuridicaModel clientePJuridicaModel) {
        ClientePJuridicaDto clientePJuridicaDto = new ClientePJuridicaDto();
        clientePJuridicaDto.setClientePJuridicaId(clientePJuridicaModel.getClientePJuridicaId());
        clientePJuridicaDto.setClienteId(clientePJuridicaModel.getClienteId().getClienteId());
        clientePJuridicaDto.setRazaoSocial(clientePJuridicaModel.getRazaoSocial());
        clientePJuridicaDto.setRepresentante(clientePJuridicaModel.getRepresentante());
        clientePJuridicaDto.setTelefone(clientePJuridicaModel.getTelefone());
        clientePJuridicaDto.setEmail(clientePJuridicaModel.getEmail());
        clientePJuridicaDto.setSite(clientePJuridicaModel.getSite());
        clientePJuridicaDto.setLogradouro(clientePJuridicaModel.getLogradouro());
        clientePJuridicaDto.setNumeroImovel(clientePJuridicaModel.getNumeroImovel());
        clientePJuridicaDto.setBairro(clientePJuridicaModel.getBairro());
        clientePJuridicaDto.setComplemento(clientePJuridicaModel.getComplemento());
        clientePJuridicaDto.setCep(clientePJuridicaModel.getCep());
        clientePJuridicaDto.setInscricaoEstatual(clientePJuridicaModel.getInscricaoEstadual());
        clientePJuridicaDto.setInscricaoMunicipal(clientePJuridicaModel.getInscricaoMunicipal());
        clientePJuridicaDto.setCnpj(clientePJuridicaModel.getCnpj());
        clientePJuridicaDto.setAtivo(clientePJuridicaModel.getAtivo());
        clientePJuridicaDto.setDataCadastro(clientePJuridicaModel.getDataCadastro());

        return clientePJuridicaDto;
    }

    // Convsersor de LIST para DTO
    public List<ClientePJuridicaDto> convertListToDto(List<ClientePJuridicaModel> clientePJuridicaList) {
        List<ClientePJuridicaDto> clientePJuridicaDtoList = new ArrayList<>();
        for (ClientePJuridicaModel clientePJuridicaModel : clientePJuridicaList) {
            ClientePJuridicaDto clientePJuridicaDto = convertModelToDto(clientePJuridicaModel);
            clientePJuridicaDtoList.add(clientePJuridicaDto);
        }
        return clientePJuridicaDtoList;
    }

}
