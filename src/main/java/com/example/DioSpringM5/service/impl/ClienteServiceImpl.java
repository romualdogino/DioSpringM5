package com.example.DioSpringM5.service.impl;

import com.example.DioSpringM5.model.Cliente;
import com.example.DioSpringM5.model.ClienteRepository;
import com.example.DioSpringM5.model.Endereco;
import com.example.DioSpringM5.model.EnderecoRepository;
import com.example.DioSpringM5.service.ClienteService;
import com.example.DioSpringM5.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarCliente(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clientedb = clienteRepository.findById(id);
        if (clientedb.isPresent()) {
            salvarCliente(cliente);
        };
    }


    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
