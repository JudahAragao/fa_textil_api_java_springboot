package com.fatextil.service;

import com.fatextil.model.FabricacaoPedidoModel;
import com.fatextil.model.TerceirizacaoModel;
import com.fatextil.model.TerceirizadoModel;
import com.fatextil.repository.FabricacaoPedidoRepository;
import com.fatextil.repository.TerceirizacaoRepository;
import com.fatextil.repository.TerceirizadoRepository;
import com.fatextil.rest.dto.TerceirizacaoDto;
import com.fatextil.rest.form.TerceirizacaoForm;
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
public class TerceirizacaoService {

    @Autowired
    private TerceirizacaoRepository terceirizacaoRepository;
    @Autowired
    private TerceirizadoRepository terceirizadoRepository;
    @Autowired
    private FabricacaoPedidoRepository fabricacaoPedidoRepository;

    public List<TerceirizacaoDto> findAll() {
        List<TerceirizacaoModel> terceirizacaoList = terceirizacaoRepository.findAll();
        return convertListToDto(terceirizacaoList);
    }

    public TerceirizacaoDto findById(Long terceirizacaoId) {
        try {
            TerceirizacaoModel terceirizacaoModel = terceirizacaoRepository.findById(terceirizacaoId)
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + terceirizacaoId + " Tipo: " + TerceirizacaoModel.class.getName()));

            return convertModelToDto(terceirizacaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + terceirizacaoId + " Tipo: " + TerceirizacaoModel.class.getName());
        }
    }

    public TerceirizacaoDto insert(TerceirizacaoForm terceirizacaoForm) {
        try {
            TerceirizacaoModel terceirizacaoNovo = convertFormToModel(terceirizacaoForm);

            terceirizacaoNovo = terceirizacaoRepository.save(terceirizacaoNovo);
            return convertModelToDto(terceirizacaoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public TerceirizacaoDto update(TerceirizacaoForm terceirizacaoForm, Long terceirizacaoId) {
        try {
            Optional<TerceirizacaoModel> terceirizacaoExistente = terceirizacaoRepository.findById(terceirizacaoId);
            if (terceirizacaoExistente.isPresent()) {
                TerceirizacaoModel terceirizacaoAtualizado = terceirizacaoExistente.get();

                // Obtém a instância de PerfilAcessoModel do repositório
                TerceirizadoModel terceirizado = terceirizadoRepository.findById(terceirizacaoForm.getTerceirizadoId())
                        .orElseThrow(() -> new RuntimeException("Terceirizada não encontrada"));
                terceirizacaoAtualizado.setTerceirizadoId(terceirizado);

                // Obtém a instância de PerfilAcessoModel do repositório
                FabricacaoPedidoModel fabricacaoPedido = fabricacaoPedidoRepository.findById(terceirizacaoForm.getFabricacaoPedidoId())
                        .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
                terceirizacaoAtualizado.setFabricacaoPedidoId(fabricacaoPedido);

                terceirizacaoAtualizado.setDataEnvio(terceirizacaoForm.getDataEnvio());
                terceirizacaoAtualizado.setHoraEnvio(terceirizacaoForm.getHoraEnvio());
                terceirizacaoAtualizado.setDataFinalizacao(terceirizacaoForm.getDataFinalizacao());
                terceirizacaoAtualizado.setHoraFinalizacao(terceirizacaoForm.getHoraFinalizacao());

                terceirizacaoRepository.save(terceirizacaoAtualizado);
                return convertModelToDto(terceirizacaoAtualizado);
            } else {
                throw new DataIntegrityException("O ID da terceirização não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da terceirização não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long terceirizacaoId) {
        try {
            if (terceirizacaoRepository.existsById(terceirizacaoId)) {
                terceirizacaoRepository.deleteById(terceirizacaoId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir a terceirização!");
        }
    }

    private TerceirizacaoModel convertFormToModel(TerceirizacaoForm terceirizacaoForm) {
        TerceirizacaoModel terceirizacaoModel = new TerceirizacaoModel();

        // Obtém a instância de PerfilAcessoModel do repositório
        TerceirizadoModel terceirizado = terceirizadoRepository.findById(terceirizacaoForm.getTerceirizadoId())
                .orElseThrow(() -> new RuntimeException("Terceirizada não encontrada"));
        terceirizacaoModel.setTerceirizadoId(terceirizado);

        // Obtém a instância de PerfilAcessoModel do repositório
        FabricacaoPedidoModel fabricacaoPedido = fabricacaoPedidoRepository.findById(terceirizacaoForm.getFabricacaoPedidoId())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        terceirizacaoModel.setFabricacaoPedidoId(fabricacaoPedido);

        terceirizacaoModel.setDataEnvio(terceirizacaoForm.getDataEnvio());
        terceirizacaoModel.setHoraEnvio(terceirizacaoForm.getHoraEnvio());
        terceirizacaoModel.setDataFinalizacao(terceirizacaoForm.getDataFinalizacao());
        terceirizacaoModel.setHoraFinalizacao(terceirizacaoForm.getHoraFinalizacao());

        return terceirizacaoModel;
    }

    private TerceirizacaoDto convertModelToDto(TerceirizacaoModel terceirizacaoModel) {
        TerceirizacaoDto terceirizacaoDto = new TerceirizacaoDto();
        terceirizacaoDto.setTerceirizacaoId(terceirizacaoModel.getTerceirizacaoId());
        terceirizacaoDto.setTerceirizadoId(terceirizacaoModel.getTerceirizadoId().getTerceirizadoId());
        terceirizacaoDto.setFabricacaoPedidoId(terceirizacaoModel.getFabricacaoPedidoId().getFabricacaoPedidoId());
        terceirizacaoDto.setDataEnvio(terceirizacaoModel.getDataEnvio());
        terceirizacaoDto.setHoraEnvio(terceirizacaoModel.getHoraEnvio());
        terceirizacaoDto.setDataFinalizacao(terceirizacaoModel.getDataFinalizacao());
        terceirizacaoDto.setHoraFinalizacao(terceirizacaoModel.getHoraFinalizacao());
        return terceirizacaoDto;
    }

    private List<TerceirizacaoDto> convertListToDto(List<TerceirizacaoModel> terceirizacaoList) {
        List<TerceirizacaoDto> terceirizacaoDtoList = new ArrayList<>();
        for (TerceirizacaoModel terceirizacaoModel : terceirizacaoList) {
            TerceirizacaoDto terceirizacaoDto = convertModelToDto(terceirizacaoModel);
            terceirizacaoDtoList.add(terceirizacaoDto);
        }
        return terceirizacaoDtoList;
    }

}
