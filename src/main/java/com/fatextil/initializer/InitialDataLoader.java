
package com.fatextil.initializer;

import com.fatextil.model.*;
import com.fatextil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InitialDataLoader implements ApplicationRunner {

    private final PerfilAcessoRepository perfilAcessoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final StatusPedidoRepository statusPedidoRepository;
    private final StatusFabricacaoRepository statusFabricacaoRepository;
    private final EtapasFabricacaoRepository etapasFabricacaoRepository;
    private final CategoriaElementoArteRepository categoriaElementoArteRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public InitialDataLoader(
            PerfilAcessoRepository perfilAcessoRepository,
            UsuarioRepository usuarioRepository,
            FuncionarioRepository funcionarioRepository,
            StatusPedidoRepository statusPedidoRepository,
            StatusFabricacaoRepository statusFabricacaoRepository,
            EtapasFabricacaoRepository etapasFabricacaoRepository,
            CategoriaElementoArteRepository categoriaElementoArteRepository,
            ClienteRepository clienteRepository) {
        this.perfilAcessoRepository = perfilAcessoRepository;
        this.usuarioRepository = usuarioRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.statusPedidoRepository = statusPedidoRepository;
        this.statusFabricacaoRepository = statusFabricacaoRepository;
        this.etapasFabricacaoRepository = etapasFabricacaoRepository;
        this.categoriaElementoArteRepository = categoriaElementoArteRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Seu código de inicialização aqui...

        // Inserir perfis de acesso padrão caso não existam
        if (perfilAcessoRepository.findByNomePerfilAcesso("ROLE_ADMIN").isEmpty()) {
            PerfilAcessoModel perfilAdmin = new PerfilAcessoModel();
            perfilAdmin.setNomePerfilAcesso("ROLE_ADMIN");
            perfilAcessoRepository.save(perfilAdmin);
        }
        if (perfilAcessoRepository.findByNomePerfilAcesso("ROLE_VENDEDOR").isEmpty()) {
            PerfilAcessoModel perfilVendedor = new PerfilAcessoModel();
            perfilVendedor.setNomePerfilAcesso("ROLE_VENDEDOR");
            perfilAcessoRepository.save(perfilVendedor);
        }
        if (perfilAcessoRepository.findByNomePerfilAcesso("ROLE_DESIGNER").isEmpty()) {
            PerfilAcessoModel perfilDesigner = new PerfilAcessoModel();
            perfilDesigner.setNomePerfilAcesso("ROLE_DESIGNER");
            perfilAcessoRepository.save(perfilDesigner);
        }

        // Inserir status de pedidos padrão caso não existam
        if (statusPedidoRepository.findByNomeStatusPedido("Fechado").isEmpty()) {
            StatusPedidoModel statusPedidoModel = new StatusPedidoModel();
            statusPedidoModel.setNomeStatusPedido("Fechado");
            statusPedidoRepository.save(statusPedidoModel);
        }
        if (statusPedidoRepository.findByNomeStatusPedido("Em Aberto").isEmpty()) {
            StatusPedidoModel statusPedidoModel = new StatusPedidoModel();
            statusPedidoModel.setNomeStatusPedido("Em Aberto");
            statusPedidoRepository.save(statusPedidoModel);
        }
        if (statusPedidoRepository.findByNomeStatusPedido("Vencido").isEmpty()) {
            StatusPedidoModel statusPedidoModel = new StatusPedidoModel();
            statusPedidoModel.setNomeStatusPedido("Vencido");
            statusPedidoRepository.save(statusPedidoModel);
        }
        if (statusPedidoRepository.findByNomeStatusPedido("Suspenso").isEmpty()) {
            StatusPedidoModel statusPedidoModel = new StatusPedidoModel();
            statusPedidoModel.setNomeStatusPedido("Suspenso");
            statusPedidoRepository.save(statusPedidoModel);
        }

        // Inserir status de etapa fabricacao padrão caso não existam
        if (statusFabricacaoRepository.findByNomeStatusFabricacao("Concluído").isEmpty()) {
            StatusFabricacaoModel statusFabricacaoModel = new StatusFabricacaoModel();
            statusFabricacaoModel.setNomeStatusFabricacao("Concluído");
            statusFabricacaoRepository.save(statusFabricacaoModel);
        }
        if (statusFabricacaoRepository.findByNomeStatusFabricacao("Andamento").isEmpty()) {
            StatusFabricacaoModel statusFabricacaoModel = new StatusFabricacaoModel();
            statusFabricacaoModel.setNomeStatusFabricacao("Andamento");
            statusFabricacaoRepository.save(statusFabricacaoModel);
        }
        if (statusFabricacaoRepository.findByNomeStatusFabricacao("Pendentes").isEmpty()) {
            StatusFabricacaoModel statusFabricacaoModel = new StatusFabricacaoModel();
            statusFabricacaoModel.setNomeStatusFabricacao("Pendentes");
            statusFabricacaoRepository.save(statusFabricacaoModel);
        }
        if (statusFabricacaoRepository.findByNomeStatusFabricacao("Suspenso").isEmpty()) {
            StatusFabricacaoModel statusFabricacaoModel = new StatusFabricacaoModel();
            statusFabricacaoModel.setNomeStatusFabricacao("Suspenso");
            statusFabricacaoRepository.save(statusFabricacaoModel);
        }

        // Inserir etapa fabricacao padrão caso não existam
        if (etapasFabricacaoRepository.findByNomeEtapa("Corte").isEmpty()) {
            EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
            etapasFabricacaoModel.setNomeEtapa("Corte");
            etapasFabricacaoRepository.save(etapasFabricacaoModel);
        }
        if (etapasFabricacaoRepository.findByNomeEtapa("Costura").isEmpty()) {
            EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
            etapasFabricacaoModel.setNomeEtapa("Costura");
            etapasFabricacaoRepository.save(etapasFabricacaoModel);
        }
        if (etapasFabricacaoRepository.findByNomeEtapa("Modelagem").isEmpty()) {
            EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
            etapasFabricacaoModel.setNomeEtapa("Modelagem");
            etapasFabricacaoRepository.save(etapasFabricacaoModel);
        }
        if (etapasFabricacaoRepository.findByNomeEtapa("Terceirizado").isEmpty()) {
            EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
            etapasFabricacaoModel.setNomeEtapa("Terceirizado");
            etapasFabricacaoRepository.save(etapasFabricacaoModel);
        }
        if (etapasFabricacaoRepository.findByNomeEtapa("Embalagem").isEmpty()) {
            EtapasFabricacaoModel etapasFabricacaoModel = new EtapasFabricacaoModel();
            etapasFabricacaoModel.setNomeEtapa("Embalagem");
            etapasFabricacaoRepository.save(etapasFabricacaoModel);
        }

        // Inserir categorias de elementos de arte padrão caso não existam
        if (categoriaElementoArteRepository.findByNomeCategoria("Exemplo").isEmpty()) {
            CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
            categoriaElementoArteModel.setNomeCategoria("Exemplo");
            categoriaElementoArteRepository.save(categoriaElementoArteModel);
        }
        if (categoriaElementoArteRepository.findByNomeCategoria("Para Arte").isEmpty()) {
            CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
            categoriaElementoArteModel.setNomeCategoria("Para Arte");
            categoriaElementoArteRepository.save(categoriaElementoArteModel);
        }
        if (categoriaElementoArteRepository.findByNomeCategoria("Retor. Arte").isEmpty()) {
            CategoriaElementoArteModel categoriaElementoArteModel = new CategoriaElementoArteModel();
            categoriaElementoArteModel.setNomeCategoria("Retor. Arte");
            categoriaElementoArteRepository.save(categoriaElementoArteModel);
        }

        // Inserir tipo de cliente caso não exista
        if (clienteRepository.findByTipoCliente("Física").isEmpty()) {
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setTipoCliente("Física");
            clienteRepository.save(clienteModel);
        }
        if (clienteRepository.findByTipoCliente("Jurídica").isEmpty()) {
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setTipoCliente("Jurídica");
            clienteRepository.save(clienteModel);
        }

        //Inserir funcionario fantasma (para admin) caso não exista
        if (funcionarioRepository.findByNome("admin").isEmpty()) {
            FuncionarioModel funcionarioModel = new FuncionarioModel();
            funcionarioModel.setNome("admin");
            funcionarioModel.setTelefone("99999999999");
            funcionarioModel.setEmail("admin@fatextil.com");
            funcionarioModel.setLogradouro("Rua Fantasma");
            funcionarioModel.setNumeroImovel("123");
            funcionarioModel.setBairro("Fantasma");
            funcionarioModel.setComplemento("");
            funcionarioModel.setCep("000000000");
            funcionarioModel.setCpf("00000000000");
            funcionarioModel.setAtivo((byte) 1);

            // Formata a data no formato "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Cria um objeto LocalDate a partir da data atual
            LocalDate dataAtual = LocalDate.now();
            // Define a data formatada no objeto FuncionarioModel
            funcionarioModel.setDataCadastro(dataAtual);

            funcionarioRepository.save(funcionarioModel);
        }

        // Inserir usuário admin padrão caso não existir
        if (usuarioRepository.findByLogin("admin").isEmpty()) {

            // Encriptar senha
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String senhaCodificada = passwordEncoder.encode("123456");

            PerfilAcessoModel perfilAdmin = perfilAcessoRepository.findByNomePerfilAcesso("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Perfil de Acesso não encontrado"));

            UsuarioModel usuarioAdmin = new UsuarioModel();
            usuarioAdmin.setPerfilAcessoId(perfilAdmin);

            // Para pegar o valor de FuncionarioModel referente ao funcionario cadastrado
            FuncionarioModel funcionario = funcionarioRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

            usuarioAdmin.setFuncionarioId(funcionario);
            usuarioAdmin.setLogin("admin");
            usuarioAdmin.setSenha(senhaCodificada);
            usuarioAdmin.setAtivo((byte) 1);

            usuarioRepository.save(usuarioAdmin);
        }
    }
}
