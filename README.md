README.md

# Projeto Crédito Express

Este projeto é uma API de simulação de empréstimo. Ele permite que os usuários simulem empréstimos com base em suas informações de crédito.

## Requisitos

Certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Java 17
- Maven
- Docker
- Docker Compose
- MongoDB Compass

## Configuração do Projeto

### Clone o Repositório

Clone o repositório do projeto para sua máquina local:

git clone https://github.com/SeuUsuario/credito-express.git
cd credito-express
Configurar e Rodar os Serviços com Docker
O projeto utiliza MongoDB como banco de dados e contém dois serviços: cliente e serviço. Vamos utilizar Docker Compose para rodar esses serviços.

Estrutura do Projeto
Certifique-se de que a estrutura do projeto seja similar a esta:



```sh
credito-express/
│
├── cliente/
│   ├── Dockerfile
│   └── (outros arquivos do serviço de cliente)
│
├── service/
│   ├── Dockerfile
│   └── (outros arquivos do serviço de serviço)
│
├── docker-compose.yml
└── (outros arquivos do projeto)
```

Rodar o Projeto com Docker Compose
Para rodar todos os serviços (MongoDB, cliente e serviço) usando Docker Compose, execute o seguinte comando na raiz do projeto:

docker-compose up --build

Acesse ao link http://localhost:8080/swagger-ui/index.html para ter acesso ao crud do cliente
Acesse ao link http://localhost:8081/swagger-ui/index.html para ter acesso ao crud da taxa e aplicação simulação

Caso necessário fazer upload das tabelas, basta acessar a pasta mongo-entrypoint e importar com mongo compass.
