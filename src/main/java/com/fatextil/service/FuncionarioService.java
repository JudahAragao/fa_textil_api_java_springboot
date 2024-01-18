package com.fatextil.service;

import com.fatextil.model.FuncionarioModel;
import com.fatextil.repository.FuncionarioRepository;
import com.fatextil.rest.dto.FuncionarioDto;
import com.fatextil.rest.form.FuncionarioForm;
import com.fatextil.rest.form.FuncionarioUpdateForm;
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
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Busca completa no banco de dados
    public List<FuncionarioDto> findAll() {
        List<FuncionarioModel> funcionarioList = funcionarioRepository.findAll();
        return convertListToDto(funcionarioList);
    }

    // Busca no banco de dados
    public FuncionarioDto findById(Long funcionarioId) {
        try {
            FuncionarioModel funcionarioModel = funcionarioRepository.findById(funcionarioId).get();
            return convertModelToDto(funcionarioModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + funcionarioId + " Tipo: " + FuncionarioModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public FuncionarioDto insert(FuncionarioForm funcionarioForm) {
        try {
            FuncionarioModel funcionarioNovo = convertFormToModel(funcionarioForm);
            Optional<FuncionarioModel> byNome = funcionarioRepository.findByNome(funcionarioNovo.getNome());
            Optional<FuncionarioModel> byTelefone = funcionarioRepository.findByTelefone(funcionarioNovo.getTelefone());
            Optional<FuncionarioModel> byEmail = funcionarioRepository.findByEmail(funcionarioNovo.getEmail());
            Optional<FuncionarioModel> byCpf = funcionarioRepository.findByCpf(funcionarioNovo.getCpf());

            if (byNome.isPresent())
                throw new IllegalStateException("Nome já registrado.");
            if (byTelefone.isPresent())
                throw new IllegalStateException("Telefone já registrado.");
            if (byEmail.isPresent())
                throw new IllegalStateException("E-mail já registrado.");
            if (byCpf.isPresent())
                throw new IllegalStateException("CPF já registrado.");

            funcionarioNovo = funcionarioRepository.save(funcionarioNovo);

            return convertModelToDto(funcionarioNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public FuncionarioDto update(FuncionarioUpdateForm funcionarioUpdateForm, Long funcionarioId) {
        try {
            Optional<FuncionarioModel> funcionarioExistente = funcionarioRepository.findById(funcionarioId);
            if (funcionarioExistente.isPresent()) {
                FuncionarioModel funcionarioAtualizado = funcionarioExistente.get();

                funcionarioAtualizado.setNome(funcionarioUpdateForm.getNome());
                funcionarioAtualizado.setTelefone(funcionarioUpdateForm.getTelefone());
                funcionarioAtualizado.setEmail(funcionarioUpdateForm.getEmail());
                funcionarioAtualizado.setLogradouro(funcionarioUpdateForm.getLogradouro());
                funcionarioAtualizado.setNumeroImovel(funcionarioUpdateForm.getNumeroImovel());
                funcionarioAtualizado.setBairro(funcionarioUpdateForm.getBairro());
                funcionarioAtualizado.setComplemento(funcionarioUpdateForm.getComplemento());
                funcionarioAtualizado.setCep(funcionarioUpdateForm.getCep());
                funcionarioAtualizado.setAtivo(funcionarioUpdateForm.getAtivo());

                funcionarioRepository.save(funcionarioAtualizado);
                return convertModelToDto(funcionarioAtualizado);
            } else {
                throw new DataIntegrityException("O ID do funcionário não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Funcionário não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long funcionarioId) {
        try {
            if (funcionarioRepository.existsById(funcionarioId)) {
                funcionarioRepository.deleteById(funcionarioId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Funcionário!");
        }
    }

    // Conversores de FORM para MODEL
    private FuncionarioModel convertFormToModel(FuncionarioForm funcionarioForm) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setNome(funcionarioForm.getNome());
        funcionarioModel.setTelefone(funcionarioForm.getTelefone());
        funcionarioModel.setEmail(funcionarioForm.getEmail());
        funcionarioModel.setLogradouro(funcionarioForm.getLogradouro());
        funcionarioModel.setNumeroImovel(funcionarioForm.getNumeroImovel());
        funcionarioModel.setBairro(funcionarioForm.getBairro());
        funcionarioModel.setComplemento(funcionarioForm.getComplemento());
        funcionarioModel.setCep(funcionarioForm.getCep());
        funcionarioModel.setCpf(funcionarioForm.getCpf());
        funcionarioModel.setAtivo(funcionarioForm.getAtivo());
        funcionarioModel.setDataCadastro(funcionarioForm.getDataCadastro());
        return funcionarioModel;
    }

    // Conversores de MODEL para DTO
    private FuncionarioDto convertModelToDto(FuncionarioModel funcionarioModel) {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(funcionarioModel.getFuncionarioId());
        funcionarioDto.setNome(funcionarioModel.getNome());
        funcionarioDto.setTelefone(funcionarioModel.getTelefone());
        funcionarioDto.setEmail(funcionarioModel.getEmail());
        funcionarioDto.setLogradouro(funcionarioModel.getLogradouro());
        funcionarioDto.setNumeroImovel(funcionarioModel.getNumeroImovel());
        funcionarioDto.setBairro(funcionarioModel.getBairro());
        funcionarioDto.setComplemento(funcionarioModel.getComplemento());
        funcionarioDto.setCep(funcionarioModel.getCep());
        funcionarioDto.setCpf(funcionarioModel.getCpf());
        funcionarioDto.setAtivo(funcionarioModel.getAtivo());
        funcionarioDto.setDataCadastro(funcionarioModel.getDataCadastro());
        return funcionarioDto;
    }

    // Conversor de LIST para DTO
    private List<FuncionarioDto> convertListToDto(List<FuncionarioModel> funcionarioList) {
        List<FuncionarioDto> funcionarioDtoList = new ArrayList<>();
        for (FuncionarioModel funcionarioModel : funcionarioList) {
            FuncionarioDto funcionarioDto = convertModelToDto(funcionarioModel);
            funcionarioDtoList.add(funcionarioDto);
        }
        return funcionarioDtoList;
    }

}
