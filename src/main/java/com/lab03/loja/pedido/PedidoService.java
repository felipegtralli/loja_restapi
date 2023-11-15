package com.lab03.loja.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedido(long id) {
        return pedidoRepository.findById(id).get();
    }

    public List<Pedido> getAllPedidosByCliente(long id) {
        return pedidoRepository.findAllByCliente(clienteRepository.findById(id).get()).get();
    }

    public Pedido gerarPedido(long id, String pagamento) {
        Cliente cliente = clienteRepository.findById(id).get();
        
        return pedidoRepository.save(new Pedido(pagamento, cliente.getCarrinho(), cliente));
    }

    public void deletePedidosByCliente(long id) {
        pedidoRepository.deleteAll(getAllPedidosByCliente(id));
    }

    public boolean pedidoExists(long id) {
        return pedidoRepository.existsById(id);
    }

    @Transactional
    public void updateStatus(long id, String status) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setStatusPedido(status);
    }
}
