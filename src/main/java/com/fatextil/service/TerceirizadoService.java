package com.fatextil.service;

import com.fatextil.model.TerceirizadoModel;
import com.fatextil.repository.TerceirizadoRepository;
import com.fatextil.rest.dto.TerceirizadoDto;
import com.fatextil.rest.form.TerceirizadoForm;
import com.fatextil.rest.form.TerceirizadoUpdateForm;
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
public class TerceirizadoService {

    @Autowired
    private TerceirizadoRepository terceirizadoRepository;

    public List<TerceirizadoDto> findAll() {
        List<TerceirizadoModel> terceirizadoList = terceirizadoRepository.findAll();
        return convertListToDto(terceirizadoList);
    }

    public TerceirizadoDto findById(Long terceirizadoId) {
        try {
            TerceirizadoModel terceirizadoModel = terceirizadoRepository.findById(terceirizadoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + terceirizadoId + " Tipo: " + TerceirizadoModel.class.getName()));

            return convertModelToDto(terceirizadoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + terceirizadoId + "Tipo: " + TerceirizadoModel.class.getName());
        }
    }

    public TerceirizadoDto insert(TerceirizadoForm terceirizadoForm) {
        try {
            TerceirizadoModel terceirizadoNovo = convertFormToModel(terceirizadoForm);

            terceirizadoNovo = terceirizadoRepository.save(terceirizadoNovo);
            return convertModelToDto(terceirizadoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public TerceirizadoDto update(TerceirizadoUpdateForm terceirizadoUpdateForm, Long terceirizadoId) {
        try {
            Optional<TerceirizadoModel> terceirizadoExistente = terceirizadoRepository.findById(terceirizadoId);
            if (terceirizadoExistente.isPresent()) {
                TerceirizadoModel terceirizadoAtualizado = terceirizadoExistente.get();

                terceirizadoAtualizado.setNome(terceirizadoUpdateForm.getNome());
                terceirizadoAtualizado.setTelefone(terceirizadoUpdateForm.getTelefone());
                terceirizadoAtualizado.setEmail(terceirizadoUpdateForm.getEmail());
                terceirizadoAtualizado.setLogradouro(terceirizadoUpdateForm.getLogradouro());
                terceirizadoAtualizado.setNumeroImovel(terceirizadoUpdateForm.getNumeroImovel());
                terceirizadoAtualizado.setBairro(terceirizadoUpdateForm.getBairro());
                terceirizadoAtualizado.setComplemento(terceirizadoUpdateForm.getComplemento());
                terceirizadoAtualizado.setCep(terceirizadoUpdateForm.getCep());
                terceirizadoAtualizado.setAtivo(terceirizadoUpdateForm.getAtivo());

                terceirizadoRepository.save(terceirizadoAtualizado);
                return convertModelToDto(terceirizadoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do terceirizado não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do terceirizado não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long terceirizadoId) {
        try {
            if (terceirizadoRepository.existsById(terceirizadoId)) {
                terceirizadoRepository.deleteById(terceirizadoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o terceirizado!");
        }
    }

    private TerceirizadoModel convertFormToModel(TerceirizadoForm terceirizadoForm) {
        TerceirizadoModel terceirizadoModel = new TerceirizadoModel();
        terceirizadoModel.setNome(terceirizadoForm.getNome());
        terceirizadoModel.setTelefone(terceirizadoForm.getTelefone());
        terceirizadoModel.setEmail(terceirizadoForm.getEmail());
        terceirizadoModel.setLogradouro(terceirizadoForm.getLogradouro());
        terceirizadoModel.setNumeroImovel(terceirizadoForm.getNumeroImovel());
        terceirizadoModel.setBairro(terceirizadoForm.getBairro());
        terceirizadoModel.setComplemento(terceirizadoForm.getComplemento());
        terceirizadoModel.setCep(terceirizadoForm.getCep());
        terceirizadoModel.setCnpj(terceirizadoForm.getCnpj());
        terceirizadoModel.setAtivo(terceirizadoForm.getAtivo());
        return terceirizadoModel;
    }

    private TerceirizadoDto convertModelToDto(TerceirizadoModel terceirizadoModel) {
        TerceirizadoDto terceirizadoDto = new TerceirizadoDto();
        terceirizadoDto.setTerceirizadoId(terceirizadoModel.getTerceirizadoId());
        terceirizadoDto.setNome(terceirizadoModel.getNome());
        terceirizadoDto.setTelefone(terceirizadoModel.getTelefone());
        terceirizadoDto.setEmail(terceirizadoModel.getEmail());
        terceirizadoDto.setLogradouro(terceirizadoModel.getLogradouro());
        terceirizadoDto.setNumeroImovel(terceirizadoModel.getNumeroImovel());
        terceirizadoDto.setBairro(terceirizadoModel.getBairro());
        terceirizadoDto.setComplemento(terceirizadoModel.getComplemento());
        terceirizadoDto.setCep(terceirizadoModel.getCep());
        terceirizadoDto.setCnpj(terceirizadoModel.getCnpj());
        terceirizadoDto.setAtivo(terceirizadoModel.getAtivo());
        terceirizadoDto.setDataCadastro(terceirizadoModel.getDataCadastro());
        return terceirizadoDto;
    }

    private List<TerceirizadoDto> convertListToDto(List<TerceirizadoModel> terceirizadoList) {
        List<TerceirizadoDto> terceirizadoDtoList = new ArrayList<>();
        for (TerceirizadoModel terceirizadoModel : terceirizadoList) {
            TerceirizadoDto terceirizadoDto = convertModelToDto(terceirizadoModel);
            terceirizadoDtoList.add(terceirizadoDto);
        }
        return terceirizadoDtoList;
    }

}
