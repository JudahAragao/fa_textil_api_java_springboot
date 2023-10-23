package com.fatextil.service;

import com.fatextil.model.ProdutoModel;
import com.fatextil.repository.ProdutoRepository;
import com.fatextil.rest.dto.ProdutoDto;
import com.fatextil.rest.form.ProdutoForm;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> findAll() {
        List<ProdutoModel> produtoList = produtoRepository.findAll();
        return convertListToDto(produtoList);
    }

    public ProdutoDto findById(Long produtoId) {
        try {
            ProdutoModel produtoModel = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + produtoId + " Tipo: " + ProdutoModel.class.getName()));
            return convertModelToDto(produtoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + produtoId + " Tipo: " + ProdutoModel.class.getName());
        }
    }

    public ProdutoDto insert(ProdutoForm produtoForm) {
        try {
            ProdutoModel produtoNovo = convertFormToModel(produtoForm);

            produtoNovo = produtoRepository.save(produtoNovo);
            return convertModelToDto(produtoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public ProdutoDto update(ProdutoForm produtoUpdateForm, Long produtoId) {
        try {
            Optional<ProdutoModel> produtoExistente = produtoRepository.findById(produtoId);
            if (produtoExistente.isPresent()) {
                ProdutoModel produtoAtualizado = produtoExistente.get();

                produtoAtualizado.setDescricao(produtoUpdateForm.getDescricaoProduto());
                produtoAtualizado.setValorProduto(produtoUpdateForm.getValorProduto());

                produtoRepository.save(produtoAtualizado);
                return convertModelToDto(produtoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do produto não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do produto não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long produtoId) {
        try {
            if (produtoRepository.existsById(produtoId)) {
                produtoRepository.deleteById(produtoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o produto!");
        }
    }

    private ProdutoModel convertFormToModel(ProdutoForm produtoForm) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setDescricao(produtoForm.getDescricaoProduto());
        produtoModel.setValorProduto(produtoForm.getValorProduto());
        return produtoModel;
    }

    private ProdutoDto convertModelToDto(ProdutoModel produtoModel) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setProdutoId(produtoModel.getCodProduto());
        produtoDto.setDescricaoProduto(produtoModel.getDescricao());
        produtoDto.setValorProduto(produtoModel.getValorProduto());
        return produtoDto;
    }

    private List<ProdutoDto> convertListToDto(List<ProdutoModel> produtoList) {
        List<ProdutoDto> produtoDtoList = new ArrayList<>();
        for (ProdutoModel produtoModel : produtoList) {
            ProdutoDto produtoDto = convertModelToDto(produtoModel);
            produtoDtoList.add(produtoDto);
        }
        return produtoDtoList;
    }

}
