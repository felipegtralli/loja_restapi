package com.lab03.loja.cliente;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab03.loja.carrinho.CarrinhoController;
import com.lab03.loja.endereco.EnderecoController;
import com.lab03.loja.exception.ClienteNotFoundException;
import com.lab03.loja.pedido.PedidoController;

@RestController
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @GetMapping(path="/api/clientes") // lista todos os clientes
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes(); // 200 OK
    }
    
    @PostMapping(path="/api/clientes") // cria cliente recebendo json body // junto criado endereco e carrinho do cliente
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) { 
        clienteService.addCliente(cliente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_cliente}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(location).build(); // 201 se criado // 409 caso email utilizado
    }

    @GetMapping(path="/api/clientes/{id_cliente}") // lista informacao de um cliente
    public EntityModel<Cliente> getCliente(@PathVariable long id_cliente) {
        try {
            Cliente cliente = clienteService.getCliente(id_cliente);

            WebMvcLinkBuilder linkCarrinho = linkTo(methodOn(CarrinhoController.class).getCarrinho(id_cliente));            
            WebMvcLinkBuilder linkEndereco = linkTo(methodOn(EnderecoController.class).getEndereco(id_cliente));
            WebMvcLinkBuilder linkPedido = linkTo(methodOn(PedidoController.class).getAllPedidosByCliente(id_cliente));

            EntityModel<Cliente> modelCliente = EntityModel.of(cliente) // add links relacionados ao cliente na resposta
                                                .add(linkEndereco.withRel("endereco"))
                                                .add(linkCarrinho.withRel("carrinho"))
                                                .add(linkPedido.withRel("pedidos"));
            
            return modelCliente; // 200 OK
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PutMapping(path="/api/clientes/{id_cliente}") // atualiza informacoes do cliente recebendo json body
    public void updateCliente(@PathVariable long id_cliente, @RequestBody Cliente cliente) {
        try {
            clienteService.updateCliente(id_cliente, cliente); //200 se atualizado // 409 caso email utilizado
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }
    
    @DeleteMapping(path="/api/clientes/{id_cliente}") // deleta cliente e todos os objetos relacionados
    public void deleteCliente(@PathVariable long id_cliente) {
        try {
            clienteService.deleteCliente(id_cliente); // 200 se deletedo
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }
}
