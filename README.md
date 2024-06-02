# Desafio

Serviço para realizar a gestão de candidatos para a entrada numa empresa.

## Indice

- [Introdução](#introdução)
- [Divisão do projeto](#divisão-do-projeto)
- [Base de dados](#base-de-dados)
- [Backend](#backend)
  - [Estrutura do projeto](#estrutura-do-projeto)
  - [Endpoints](#endpoints)
  - [Notas de desenvolvimento backend](#notas-de-desenvolvimento-backend)
  - [Utilização](#utilização)
- [Frontend](#frontend)
  - [Notas de desenvolvimento frontend](#notas-de-desenvolvimento-frontend)
  - [Utilização](#utilização)
- [Testes](#testes)
  - [Ferramentas utilizadas](#ferramentas-utilizadas)

## Introdução

O desafio consiste em criar um serviço para realizar a gestão de candidatos para a entrada numa empresa.
Para tal, é necessário criar um Web Service e um Client.
Foram utilizadas as tecnologias Java, Spring Boot, MySQL, HTML, CSS e JavaScript.
Postman e JUnit foram utilizados para testar o serviço.

## Divisão do projeto

- Backend -> pasta desafio
- Frontend -> pasta desafio-frontend

Outros elementos:

- .env.example -> exemplo de variáveis de ambiente
- ResumoDesafio.md -> resumo do desafio
- README.md -> documentação do projeto
- Postman collection -> desafio/Desafio.postman_collection.json

## Base de dados

Foram criadas duas tabelas: Candidatos e Profissões.

### Tabela Candidatos

```sql
ID (gerado automaticamente)
NOME
CONTACTO
IDADE
MORADA
PROFISSAO
```

Nota: A coluna PROFISSAO tem uma relação muitos para um com a tabela Profissões.

### Tabela Profissões

```sql
ID
DESCRICAO 
```

Nota: Na run do projeto, a tabela Profissões é preenchida automaticamente, se ainda não estiver preenchida, com os seguintes dados: Estudante, Desempregado, Trabalhador por conta de outrem, Trabalhador por conta própria, Outro.

## Backend

O backend foi desenvolvido em Java com Spring Boot, utilizando uma base de dados MySQL.

### Estrutura do projeto

- Controller -> endpoints
- Service -> regras de negócio
- Repository -> acesso à base de dados
- Model -> entidades

Fluxo de desenvolvimento

```bash
Controller -> Service -> Repository -> Model
```

### Endpoints

- GET /candidatos - obter todos os candidatos
- GET /candidatos/{id} - obter um candidato
- POST /candidatos - criar um candidato
- PUT /candidatos/{id} - editar um candidato
- DELETE /candidatos/{id} - eliminar um candidato
- GET /candidatos/profissoes/{profissoes_id} - obter todos os candidatos com uma determinada profissão (este endpoint não foi implementado no frontend)
- GET /profissoes - obter todas as profissões

#### Notas de desenvolvimento backend

- application.properties -> inserção das propriedades da base de dados (MySQL)
- .env -> variáveis de ambiente para acesso à base de dados
- pom.xml -> dependências do projeto

### Utilização

1. Criar a base de dados no MySQL
2. Definir as variáveis de ambiente no ficheiro .env com os dados relativos à bd (um ficheiro .env.example está disponível)

```bash
MYSQL_URL   = "<URL>"    ->    Exemplo:"localhost:3306"
MYSQL_DATABASE = "<Nome BD>"
MYSQL_USER  = "<User>"
MYSQL_PASSWORD  = "<Password>"
```

3. Executar o projeto

## Frontend

O frontend foi desenvolvido em HTML, CSS e JavaScript.

### Notas de desenvolvimento frontend

- index.html -> página principal
- style.css -> estilos
- script.js -> lógica do frontend

### Utilização

- Abrir o ficheiro index.html no browser

## Testes

Foram apenas realizados testes unitários. No entanto, seria interessante realizar testes de integração e testes de aceitação visto que estes são mais abrangentes.

### Ferramentas utilizadas

- Postman (testar os endpoints) manualmente
- JUnit - testes unitários

### Utilização

- Executar os testes unitários

## Autor

**Pedro Pedrosa**

- [LinkedIn](https://www.linkedin.com/in/pedro-pedrosa-492537126/)
- [GitHub](https://github.com/pmspedrosa)