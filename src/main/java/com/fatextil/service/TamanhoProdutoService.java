package com.fatextil.service;

import com.fatextil.model.ProdutoModel;
import com.fatextil.model.TamanhoProdutoModel;
import com.fatextil.repository.ProdutoRepository;
import com.fatextil.repository.TamanhoProdutoRepository;
import com.fatextil.rest.dto.TamanhoProdutoDto;
import com.fatextil.rest.form.TamanhoProdutoForm;
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
public class TamanhoProdutoService {

    @Autowired
    private TamanhoProdutoRepository tamanhoProdutoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<TamanhoProdutoDto> findAll() {
        List<TamanhoProdutoModel> tamanhoProdutoList = tamanhoProdutoRepository.findAll();
        return convertListToDto(tamanhoProdutoList);
    }

    public TamanhoProdutoDto findById(Long produtoId) {
        try {
            TamanhoProdutoModel tamanhoProdutoModel = tamanhoProdutoRepository.findById(produtoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + produtoId + " Tipo: " + TamanhoProdutoModel.class.getName()));

            return convertModelToDto(tamanhoProdutoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + produtoId + " Tipo: " + TamanhoProdutoModel.class.getName());
        }
    }

    public TamanhoProdutoDto insert(TamanhoProdutoForm tamanhoProdutoForm) {
        try {
            TamanhoProdutoModel tamanhoProdutoNovo = convertFormToModel(tamanhoProdutoForm);

            tamanhoProdutoNovo = tamanhoProdutoRepository.save(tamanhoProdutoNovo);
            return convertModelToDto(tamanhoProdutoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public TamanhoProdutoDto update(TamanhoProdutoForm tamanhoProdutoForm, Long tamanhoProdutoId) {
        try {
            Optional<TamanhoProdutoModel> tamanhoProdutoExistente = tamanhoProdutoRepository.findById(tamanhoProdutoId);
            if (tamanhoProdutoExistente.isPresent()) {
                TamanhoProdutoModel tamanhoProdutoAtualizado = tamanhoProdutoExistente.get();

                // Defina o relacionamento para ProdutoModel
                ProdutoModel produto = produtoRepository.findById(tamanhoProdutoForm.getCodProduto())
                        .orElseThrow(() -> new RuntimeException("produto não encontrado"));
                tamanhoProdutoAtualizado.setCodProduto(produto);

                tamanhoProdutoAtualizado.setTamanho(tamanhoProdutoForm.getTamanho());

                tamanhoProdutoRepository.save(tamanhoProdutoAtualizado);
                return convertModelToDto(tamanhoProdutoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do tamanho de produto não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do tamanho de produto não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long tamanhoProdutoId) {
        try {
            if (tamanhoProdutoRepository.existsById(tamanhoProdutoId)) {
                tamanhoProdutoRepository.deleteById(tamanhoProdutoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o tamanho de produto!");
        }
    }

    private TamanhoProdutoModel convertFormToModel(TamanhoProdutoForm tamanhoProdutoForm) {
        TamanhoProdutoModel tamanhoProdutoModel = new TamanhoProdutoModel();

        // Definir o relacionamento para ProdutoModel
        ProdutoModel produto = produtoRepository.findById(tamanhoProdutoForm.getCodProduto())
                .orElseThrow(() -> new RuntimeException("produto não encontrado"));
        tamanhoProdutoModel.setCodProduto(produto);

        tamanhoProdutoModel.setTamanho(tamanhoProdutoForm.getTamanho());
        return tamanhoProdutoModel;
    }

    private TamanhoProdutoDto convertModelToDto(TamanhoProdutoModel tamanhoProdutoModel) {
        TamanhoProdutoDto tamanhoProdutoDto = new TamanhoProdutoDto();
        tamanhoProdutoDto.setId(tamanhoProdutoModel.getTamanhoProdutoId());
        tamanhoProdutoDto.setCodProduto(tamanhoProdutoModel.getCodProduto().getCodProduto());
        tamanhoProdutoDto.setTamanho(tamanhoProdutoModel.getTamanho());
        return tamanhoProdutoDto;
    }

    private List<TamanhoProdutoDto> convertListToDto(List<TamanhoProdutoModel> tamanhoProdutoList) {
        List<TamanhoProdutoDto> tamanhoProdutoDtoList = new ArrayList<>();
        for (TamanhoProdutoModel tamanhoProdutoModel : tamanhoProdutoList) {
            TamanhoProdutoDto tamanhoProdutoDto = convertModelToDto(tamanhoProdutoModel);
            tamanhoProdutoDtoList.add(tamanhoProdutoDto);
        }
        return tamanhoProdutoDtoList;
    }

}
