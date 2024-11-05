# RapidShelf API

API para um marketplace de produtos próximos do vencimento entre empresas e indústrias. O objetivo do RapidShelf é facilitar a venda e o aproveitamento de produtos que estão perto do vencimento, reduzindo o desperdício e promovendo uma economia circular entre negócios.

## Sumário

*   [Requisitos](#requisitos)
*   [Instalação](#instalacao)
*   [Iniciando o Projeto](#iniciando-projeto)
*   [Estrutura de Endpoints](#estrutura-endpoints)
*   [Autenticação](#autenticacao)
*   [Usuários](#usuarios)
*   [Produtos](#produtos)
*   [Tratamento de Erros](#tratamento-erros)
*   [Contribuição](#contribuicao)
*   [Licença](#licenca)

## Requisitos

*   Docker e Docker Compose instalados na máquina.
*   JDK 21 (para desenvolvimento fora do Docker).
*   Maven (para desenvolvimento fora do Docker).

## Instalação

Clone o repositório para a sua máquina local:

```
git clone https://github.com/seu-usuario/rapidshelf.git
cd rapidshelf
```

## Iniciando o Projeto

Para iniciar o projeto usando Docker, execute o seguinte comando na raiz do projeto:

```
docker-compose up
```

Esse comando inicializa o RapidShelf e suas dependências, como o banco de dados. O serviço estará disponível em [http://localhost:8080](http://localhost:8080).

## Estrutura de Endpoints

### Autenticação

#### POST /auth/register

Registra um novo usuário no sistema.

**Corpo da Requisição:**

```
{
  "name": "Nome do Usuário",
  "email": "usuario@example.com",
  "password": "senha123",
  "role": "ADMIN" // ou "USER"
}
```

**Resposta:**

*   200 OK - Registro bem-sucedido.
*   409 Conflict - Usuário já registrado.

#### POST /auth/login

Autentica um usuário e retorna um token JWT.

**Corpo da Requisição:**

```
{
  "email": "usuario@example.com",
  "password": "senha123"
}
```

**Resposta:**

*   200 OK - Retorna o token JWT.
*   401 Unauthorized - Credenciais inválidas.

### Usuários

#### GET /users

Retorna o perfil do usuãrio


**Resposta:**

*   200 OK - Informações do usuário.
*   404 Not Found - Usuário não encontrado.

### Produtos

#### POST /products

Cria um novo produto no marketplace.

**Corpo da Requisição:**

```
{
  "name": "Produto X",
  "description": "Descrição do Produto X",
  "expirationDate": "2024-12-31",
  "price": 100.0,
  "status": "ACTIVE" // ou "INACTIVE"
}
```

**Resposta:**

*   201 Created - Produto criado com sucesso.
*   400 Bad Request - Dados inválidos.

#### GET /products

Lista todos os produtos ativos no marketplace.

**Resposta:**

*   200 OK - Lista de produtos ativos.

#### PATCH /products/{productId}/status

Atualiza o status de um produto (por exemplo, ACTIVE ou REPROVED).

**Parâmetros de URL:**

*   `productId` - ID do produto a ser atualizado.

**Corpo da Requisição:**

```
{
  "status": "ACTIVE" // ou "REPROVED"
}
```

**Resposta:**

*   200 OK - Status do produto atualizado.
*   404 Not Found - Produto não encontrado.
*   400 Bad Request - Status inválido.

## Tratamento de Erros

O RapidShelf usa respostas de erro consistentes para facilitar a integração:

*   400 Bad Request - Requisição inválida (ex: parâmetros incorretos ou campos ausentes).
*   401 Unauthorized - Falha de autenticação.
*   403 Forbidden - Acesso negado.
*   404 Not Found - Recurso não encontrado.
*   409 Conflict - Conflito de dados (ex: usuário já registrado).

## Contribuição

Para contribuir com o RapidShelf:

*   Crie uma branch para a nova funcionalidade:

```
git checkout -b minha-funcionalidade
```

*   Realize suas alterações e faça commit:

```
git commit -m "Minha nova funcionalidade"
```

*   Envie suas alterações:

```
git push origin minha-funcionalidade
```

*   Abra um pull request para análise.

## Licença

Este projeto está licenciado sob a MIT License. Consulte o arquivo LICENSE para mais detalhes.
