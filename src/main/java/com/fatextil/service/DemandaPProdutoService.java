package com.fatextil.service;

import com.fatextil.model.DemandaPProdutoModel;
import com.fatextil.model.TamanhoProdutoModel;
import com.fatextil.repository.DemandaPProdutoRepository;
import com.fatextil.repository.TamanhoProdutoRepository;
import com.fatextil.rest.dto.DemandaPProdutoDto;
import com.fatextil.rest.form.DemandaPProdutoForm;
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
public class DemandaPProdutoService {

    @Autowired
    private DemandaPProdutoRepository demandaPProdutoRepository;
    @Autowired
    private TamanhoProdutoRepository tamanhoProdutoRepository;

    // Busca completa no banco de dados
    public List<DemandaPProdutoDto> findAll() {
        List<DemandaPProdutoModel> demandaPProdutoList = demandaPProdutoRepository.findAll();
        return convertListToDto(demandaPProdutoList);
    }

    // Busca no banco de dados
    public DemandaPProdutoDto findById(Long demandaPProdutoId) {
        try {
            DemandaPProdutoModel demandaPProdutoModel = demandaPProdutoRepository.findById(demandaPProdutoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + demandaPProdutoId + " Tipo: " + DemandaPProdutoModel.class.getName()));

            return convertModelToDto(demandaPProdutoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + demandaPProdutoId + " Tipo: " + DemandaPProdutoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public DemandaPProdutoDto insert(DemandaPProdutoForm demandaPProdutoForm) {
        try {
            DemandaPProdutoModel demandaPProdutoNovo = convertFormToModel(demandaPProdutoForm);

            demandaPProdutoNovo = demandaPProdutoRepository.save(demandaPProdutoNovo);
            return convertModelToDto(demandaPProdutoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public DemandaPProdutoDto update(DemandaPProdutoForm demandaPProdutoForm, Long demandaPProdutoId) {
        try {
            Optional<DemandaPProdutoModel> demandaPProdutoExistente = demandaPProdutoRepository.findById(demandaPProdutoId);
            if (demandaPProdutoExistente.isPresent()) {
                DemandaPProdutoModel demandaPProdutoAtualizado = demandaPProdutoExistente.get();

                demandaPProdutoAtualizado.setDescricao(demandaPProdutoForm.getDescricaoDemanda());
                demandaPProdutoAtualizado.setUnidadeMedida(demandaPProdutoForm.getUnidadeMedida());
                demandaPProdutoAtualizado.setQtdeDemandada(demandaPProdutoForm.getQtdeDemandada());
                demandaPProdutoAtualizado.setCustoUnitarioDemanda(demandaPProdutoForm.getCustoUnitarioDemandado());

                demandaPProdutoRepository.save(demandaPProdutoAtualizado);
                return convertModelToDto(demandaPProdutoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do demandaPProduto não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da DemandaPProduto não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long demandaPProdutoId) {
        try {
            if (demandaPProdutoRepository.existsById(demandaPProdutoId)) {
                demandaPProdutoRepository.deleteById(demandaPProdutoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma DemandaPProduto!");
        }
    }

    // Conversores de FORM para MODEL
    public DemandaPProdutoModel convertFormToModel(DemandaPProdutoForm demandaPProdutoForm) {
        DemandaPProdutoModel demandaPProdutoModel = new DemandaPProdutoModel();

        TamanhoProdutoModel tamanhoProduto = tamanhoProdutoRepository.findById(demandaPProdutoForm.getTamanhoProdutoId())
                .orElseThrow(() -> new RuntimeException("Tamanho Produto não encontrada"));
        demandaPProdutoModel.setTamanhoProdutoId(tamanhoProduto);

        demandaPProdutoModel.setDescricao(demandaPProdutoForm.getDescricaoDemanda());
        demandaPProdutoModel.setUnidadeMedida(demandaPProdutoForm.getUnidadeMedida());
        demandaPProdutoModel.setQtdeDemandada(demandaPProdutoForm.getQtdeDemandada());
        demandaPProdutoModel.setCustoUnitarioDemanda(demandaPProdutoForm.getCustoUnitarioDemandado());

        return demandaPProdutoModel;
    }

    // Conversores de MODEL para DTO
    public DemandaPProdutoDto convertModelToDto(DemandaPProdutoModel demandaPProdutoModel) {
        DemandaPProdutoDto demandaPProdutoDto = new DemandaPProdutoDto();

        demandaPProdutoDto.setDemandaPProdutoId(demandaPProdutoModel.getDemandaPProdutoId());
        demandaPProdutoDto.setTamanhoProdutoId(demandaPProdutoModel.getTamanhoProdutoId().getTamanhoProdutoId());
        demandaPProdutoDto.setDescricao(demandaPProdutoModel.getDescricao());
        demandaPProdutoDto.setUnidadeMedida(demandaPProdutoModel.getUnidadeMedida());
        demandaPProdutoDto.setQtdeDemandada(demandaPProdutoModel.getQtdeDemandada());
        demandaPProdutoDto.setCustoUnitarioDemandado(demandaPProdutoModel.getCustoUnitarioDemanda());

        return demandaPProdutoDto;
    }

    // Conversor de LIST para DTO
    public List<DemandaPProdutoDto> convertListToDto(List<DemandaPProdutoModel> demandaPProdutoList) {
        List<DemandaPProdutoDto> demandaPProdutoDtoList = new ArrayList<>();
        for (DemandaPProdutoModel demandaPProdutoModel : demandaPProdutoList) {
            DemandaPProdutoDto demandaPProdutoDto = convertModelToDto(demandaPProdutoModel);
            demandaPProdutoDtoList.add(demandaPProdutoDto);
        }
        return demandaPProdutoDtoList;
    }

}
