package com.fatextil.service;

import com.fatextil.model.ItensPedidoModel;
import com.fatextil.model.PedidoModel;
import com.fatextil.model.ProdutoModel;
import com.fatextil.repository.ItensPedidoRepository;
import com.fatextil.repository.PedidoRepository;
import com.fatextil.repository.ProdutoRepository;
import com.fatextil.rest.dto.ItensPedidoDto;
import com.fatextil.rest.form.ItensPedidoForm;
import com.fatextil.rest.form.ItensPedidoUpdateForm;
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
public class ItensPedidoService {

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    // Busca completa no banco de dados
    public List<ItensPedidoDto> findAll() {
        List<ItensPedidoModel> itensPedidoList = itensPedidoRepository.findAll();
        return convertListToDto(itensPedidoList);
    }

    // Busca no banco de dados
    public ItensPedidoDto findById(Long itensPedidoId) {
        try {
            ItensPedidoModel itensPedidoModel = itensPedidoRepository.findById(itensPedidoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + itensPedidoId + " Tipo: " + ItensPedidoModel.class.getName()));
            return convertModelToDto(itensPedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + itensPedidoId + " Tipo: " + ItensPedidoModel.class.getName());
        }
    }

    // Inserção no banco de dados
    public ItensPedidoDto insert(ItensPedidoForm itensPedidoForm) {
        try {
            ItensPedidoModel itensPedidoNovo = convertFormToModel(itensPedidoForm);
            itensPedidoNovo = itensPedidoRepository.save(itensPedidoNovo);
            return convertModelToDto(itensPedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    // Alteração no banco de dados
    public ItensPedidoDto update(ItensPedidoUpdateForm itensPedidoUpdateForm, Long itensPedidoId) {
        try {
            Optional<ItensPedidoModel> itensPedidoExistente = itensPedidoRepository.findById(itensPedidoId);
            if (itensPedidoExistente.isPresent()) {
                ItensPedidoModel itensPedidoAtualizado = itensPedidoExistente.get();
                itensPedidoAtualizado.setDescricao(itensPedidoUpdateForm.getDescricao());
                itensPedidoAtualizado.setQtde(itensPedidoUpdateForm.getQtde());
                itensPedidoAtualizado.setObservacao(itensPedidoUpdateForm.getObservacao());
                itensPedidoRepository.save(itensPedidoAtualizado);
                return convertModelToDto(itensPedidoAtualizado);
            } else {
                throw new DataIntegrityException("O ID do item do pedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do item do pedido não foi(foram) preenchido(s).");
        }
    }

    // Exclusão no banco de dados
    public void delete(Long itensPedidoId) {
        try {
            if (itensPedidoRepository.existsById(itensPedidoId)) {
                itensPedidoRepository.deleteById(itensPedidoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um item do pedido!");
        }
    }

    // Conversores de FORM para MODEL
    private ItensPedidoModel convertFormToModel(ItensPedidoForm itensPedidoForm) {
        ItensPedidoModel itensPedidoModel = new ItensPedidoModel();

        // Defina os relacionamentos para CodPedido e CodProduto
        PedidoModel pedido = pedidoRepository.findById(itensPedidoForm.getCodPedido())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        itensPedidoModel.setCodPedido(pedido);

        ProdutoModel produto = produtoRepository.findById(itensPedidoForm.getCodProduto())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        itensPedidoModel.setCodProduto(produto);

        itensPedidoModel.setDescricao(itensPedidoForm.getDescricao());
        itensPedidoModel.setQtde(itensPedidoForm.getQtde());
        itensPedidoModel.setObservacao(itensPedidoForm.getObservacao());

        return itensPedidoModel;
    }

    // Conversores de MODEL para DTO
    private ItensPedidoDto convertModelToDto(ItensPedidoModel itensPedidoModel) {
        ItensPedidoDto itensPedidoDto = new ItensPedidoDto();

        itensPedidoDto.setItensPedidoId(itensPedidoModel.getItensPedidoId());
        itensPedidoDto.setCodPedido(itensPedidoModel.getCodPedido().getCodPedido());
        itensPedidoDto.setCodProduto(itensPedidoModel.getCodProduto().getCodProduto());
        itensPedidoDto.setDescricao(itensPedidoModel.getDescricao());
        itensPedidoDto.setQtde(itensPedidoModel.getQtde());
        itensPedidoDto.setObservacao(itensPedidoModel.getObservacao());

        return itensPedidoDto;
    }

    // Conversor de LIST para DTO
    private List<ItensPedidoDto> convertListToDto(List<ItensPedidoModel> itensPedidoList) {
        List<ItensPedidoDto> itensPedidoDtoList = new ArrayList<>();
        for (ItensPedidoModel itensPedidoModel : itensPedidoList) {
            ItensPedidoDto itensPedidoDto = convertModelToDto(itensPedidoModel);
            itensPedidoDtoList.add(itensPedidoDto);
        }
        return itensPedidoDtoList;
    }

}
