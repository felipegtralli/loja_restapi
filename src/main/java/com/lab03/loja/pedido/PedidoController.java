package com.lab03.loja.pedido;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab03.loja.carrinho.CarrinhoService;
import com.lab03.loja.exception.EmptyCarrinhoException;
import com.lab03.loja.exception.ClienteNotFoundException;
import com.lab03.loja.exception.PedidoNotFoundException;

@RestController
public class PedidoController {
    private PedidoService pedidoService;
    private CarrinhoService carrinhoService;

    @Autowired
    public PedidoController(PedidoService pedidoService, CarrinhoService carrinhoService) {
        this.pedidoService = pedidoService;
        this.carrinhoService = carrinhoService;
    }

    @GetMapping(path="/api/pedidos") // lista todos os pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos(); // 200 OK
    }

    @GetMapping(path="/api/pedidos/{id_pedido}") // lista informacao do pedido
    public Pedido getPedido(@PathVariable long id_pedido) {
        try {
            return pedidoService.getPedido(id_pedido); // 200 OK
        }
        catch(NoSuchElementException ex) {
            throw new PedidoNotFoundException("pedido id=" + id_pedido + " not found"); // 404 se pedido nao existe
        }
    }

    @GetMapping(path="/api/clientes/{id_cliente}/pedidos") // lista todos os pedidos de um cliente
    public List<Pedido> getAllPedidosByCliente(@PathVariable long id_cliente) {
        try {
            return pedidoService.getAllPedidosByCliente(id_cliente); // 200 Ok
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PostMapping(path="/api/clientes/{id_cliente}/pedidos") // gera pedido do cliente; recebe query param: ?pagamento=?
    public ResponseEntity<Pedido> gerarPedido(@PathVariable long id_cliente, @RequestParam String pagamento) {
        try {
            if(carrinhoService.carrinhoIsEmpty(id_cliente)) throw new EmptyCarrinhoException("carrinho is empty"); // 400 se carrinho vazio

            Pedido pedido = pedidoService.gerarPedido(id_cliente, pagamento);
            carrinhoService.esvaziaCarrinho(pedido.getCarrinho());

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
            return ResponseEntity.created(location).build(); // 201 se pedido criado
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PatchMapping(path="/api/pedidos/{id_pedido}") // atualiza status do pedido; recebe query param: ?status=?
    public void updateStatus(@PathVariable long id_pedido, @RequestParam String status) {
        try {
            pedidoService.updateStatus(id_pedido, status); // 200 se atualizado
        }
        catch(NoSuchElementException ex) {
            throw new PedidoNotFoundException("pedido id=" + id_pedido + " not found"); // 404 se pedido nao existe
        }
    }

}
