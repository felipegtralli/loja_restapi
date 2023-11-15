# Loja Application
Projeto do Laboratório 3, do componente curricular Programação Orientada à Objetos.

# Sobre o projeto 
Loja Application, é um projeto do 3° laboratório do componente currícular Programação Orientada à Objetos, ministrada pelo professor José Matias Leme Filho, na Universidade São Francisco, em Bragança Paulista.

O projeto consiste no desenvolvimento de um back-end completo de uma loja virtual, utilizando a POO, através da linguagem de programação Java e o framework Spring Boot.

Esta loja permite aos usuários realizar cadastro, navegar por produtos, selecionar itens para um carrinho de compras e concluir a compra gerando um pedido.

## Modelo conceitual
https://github.com/felipegtralli/loja_restapi/blob/master/lab03.pdf

# Tecnologias utilizadas

- Java
- Spring Boot
- MySQL
- Maven

# Funcionalidades

## Clientes:
	Listar todos os clientes Get(/api/clientes)
	Adicionar clientes Post(/api/clientes)
	Obter cliente por ID Get(/api/clientes/{id_cliente})
	Deletar cliente por ID(/api/clientes/{id_cliente})
	Alterar informacao do cliente Put(/api/clientes/{id_cliente})

## Carrinho:
	Obter carrinho por cliente Get(/api/clientes/{id_cliente}/carrinho)
	Adicionar produto no carrinho Post(/api/clientes/{id_cliente}/carrinho)
	Remover produto do carrinho Patch(/api/clientes/{id_cliente}/carrinho)
	Alterar quantidade do produto do carrinho Patch(/api/clientes/{id_cliente}/carrinho/{id_produto})
	Esvaziar itens do carrinho Delete(/api/clientes/{id_cliente}/carrinho)

## Endereco:
	Obter endereco por cliente Get(/api/clientes/{id_cliente}/endereco)
	Alterar endereco Put(/api/clientes/{id_cliente}/endereco)
	Esvaziar endereco Delete(/api/clientes/{id_cliente}/endereco)

## Pedidos:
	Listar todos os pedidos Get(/api/pedidos)
	Obter pedido por ID Get(/api/pedidos/{id_pedido})
	Obter todos os pedidos de um cliente Get(/api/clientes/{id_cliente}/pedidos)
	Gerar pedido de um cliente Post(/api/clientes/{id_cliente}/pedidos)
	Alterar status do pedido Patch(/api/pedidos/{id_pedido})

## Produtos:
	Listar todos os produtos Get(/api/produtos)
	Adicionar produto Post(/api/produtos)
	Obter produto por ID Get(/api/produtos/{id_produto})
 	Alterar valor do produto Patch(/api/produtos/{id_produto})
	Deletar produto Delete(/api/produtos/{id_produto})

 # Como executar o projeto 

Pré requisitos : Java, Banco de dados no MySQL
```bash
# clonar repositório
git clone https://github.com/felipegtralli/loja_restapi.git

# Banco de dados 
setar as informações no application-properties
```

#Autores

- Felipe Tralli - RA: 202104643
- Mateus Montini - RA: 202100862


