package com.fatextil.service;

import com.fatextil.model.FuncionarioModel;
import com.fatextil.model.PerfilAcessoModel;
import com.fatextil.model.UsuarioModel;
import com.fatextil.repository.FuncionarioRepository;
import com.fatextil.repository.PerfilAcessoRepository;
import com.fatextil.repository.UsuarioRepository;
import com.fatextil.rest.dto.UsuarioDto;
import com.fatextil.rest.form.UsuarioForm;
import com.fatextil.rest.form.UsuarioUpdateForm;
import com.fatextil.service.exceptions.DataIntegrityException;
import com.fatextil.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<UsuarioDto> findAll() {
        List<UsuarioModel> usuarioList = usuarioRepository.findAll();
        return convertListToDto(usuarioList);
    }

    public UsuarioDto findById(Long usuarioId) {
        try {
            UsuarioModel usuarioModel = usuarioRepository.findById(usuarioId).get();
            return convertModelToDto(usuarioModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + usuarioId + " Tipo: " + UsuarioModel.class.getName());
        }
    }

    public UsuarioDto insert(UsuarioForm usuarioForm) {
        try {
            UsuarioModel usuarioNovo = convertFormToModel(usuarioForm);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String senhaCodificada = passwordEncoder.encode(usuarioForm.getSenha());
            usuarioNovo.setSenha(senhaCodificada);

            Optional<UsuarioModel> byLogin = usuarioRepository.findByLogin(usuarioNovo.getLogin());

            if (byLogin.isPresent())
                throw new IllegalStateException("Login já registrado.");

            usuarioNovo = usuarioRepository.save(usuarioNovo);

            return convertModelToDto(usuarioNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public UsuarioDto update(UsuarioUpdateForm usuarioUpdateForm, Long usuarioId) {
        try {
            Optional<UsuarioModel> usuarioExistente = usuarioRepository.findById(usuarioId);
            if (usuarioExistente.isPresent()) {
                UsuarioModel usuarioAtualizado = usuarioExistente.get();

                usuarioAtualizado.setLogin(usuarioUpdateForm.getLogin());
                usuarioAtualizado.setSenha(usuarioUpdateForm.getSenha());
                usuarioAtualizado.setAtivo(usuarioUpdateForm.getAtivo());

                usuarioRepository.save(usuarioAtualizado);
                return convertModelToDto(usuarioAtualizado);
            } else {
                throw new DataIntegrityException("O ID do usuário não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do usuário não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long usuarioId) {
        try {
            if (usuarioRepository.existsById(usuarioId)) {
                usuarioRepository.deleteById(usuarioId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um usuário!");
        }
    }

    private UsuarioModel convertFormToModel(UsuarioForm usuarioForm) {
        UsuarioModel usuarioModel = new UsuarioModel();

        // Obtém a instância de PerfilAcessoModel do repositório
        PerfilAcessoModel perfilAcesso = perfilAcessoRepository.findById(usuarioForm.getPerfilAcessoId())
                .orElseThrow(() -> new RuntimeException("Perfil de acesso não encontrado"));
        usuarioModel.setPerfilAcessoId(perfilAcesso);

        // Obtém a instância de FuncionarioModel do repositório
        FuncionarioModel funcionario = funcionarioRepository.findById(usuarioForm.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        usuarioModel.setFuncionarioId(funcionario);

        usuarioModel.setLogin(usuarioForm.getLogin());
        usuarioModel.setSenha(usuarioForm.getSenha());
        usuarioModel.setAtivo(usuarioForm.getAtivo());
        return usuarioModel;
    }

    private UsuarioDto convertModelToDto(UsuarioModel usuarioModel) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioModel.getUsuarioId());
        usuarioDto.setPerfilAcessoId(usuarioModel.getPerfilAcessoId().getPerfilAcessoId());
        usuarioDto.setFuncionarioId(usuarioModel.getFuncionarioId().getFuncionarioId());
        usuarioDto.setLogin(usuarioModel.getLogin());
        usuarioDto.setSenha(usuarioModel.getSenha());
        usuarioDto.setAtivo(usuarioModel.getAtivo());
        return usuarioDto;
    }

    private List<UsuarioDto> convertListToDto(List<UsuarioModel> usuarioList) {
        List<UsuarioDto> usuarioDtoList = new ArrayList<>();
        for (UsuarioModel usuarioModel : usuarioList) {
            UsuarioDto usuarioDto = convertModelToDto(usuarioModel);
            usuarioDtoList.add(usuarioDto);
        }
        return usuarioDtoList;
    }

}
