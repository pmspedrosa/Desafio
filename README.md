# Desafio

## Web Service

### Responsável pela gestão e persistência dos dados:
* Criar candidato (um ou vários) - (POST /candidatos) - Recebe lista de candidatos
* Editar candidato - (PUT /candidatos/{id})
* Eliminar candidato - (DELETE /candidatos/{id})
* Obter dados de todas os candidatos - (GET /candidatos)
* Obter dados de um candidato - (GET /candidatos/{id})

### Tabela Candidatos:
* ID (gerado automaticamente)
* NOME 
* CONTACTO 
* IDADE 
* MORADA 
* PROFISSAO (deverá estar relacionado com a tabela Profissao)

### Tabela Profissao:
* ID
* DESCRICAO (inserir os seguintes dados: Estudante, Desempregado, Trabalhador por conta de outrem, 
Trabalhador por conta própria, Outro)

### Notas:
* criar serviço -> Java (Spring Boot)
* a persistência dos dados pode ser realizada com uma BD á tua escolha


## Client


### O Client é responsável pelo utilizador poder realizar todas as operações com as pessoas:

### Criar Website apenas com uma página, sem autenticação, com as seguintes features:
* formulário para adicionar candidato (A profissão a escolher deverá estar numa dropdown)
* botão para adicionar candidato
* tabela com todos os candidatos e dados
* no final de cada linha da tabela deverá ter um botão para eliminar o candidato
* no final de cada linha da tabela deverá ter um botão para editar o candidato (abre popup com formulário pré-preenchido com os dados atuais, e botão save)



## Tecnologias utilizadas Backend
* Java
* Spring Boot
* MySQL

### Notas de desenvolvimento
* application.properties -> propriedades da base de dados (MySQL)
* pom.xml -> dependências do projeto
* @Autowire - injeção de dependências 
* @RestControler - indica que é um controlador REST
* .env -> variáveis de ambiente para acesso à base de dados

### Fluxo de desenvolvimento
Controller -> Service -> Repository -> Model

### Testes
* Postman (testar os endpoints) manualmente
* JUnit


## Tecnologias utilizadas Frontend
* HTML
* CSS
* JavaScript

### Notas de desenvolvimento
* index.html -> página principal
* style.css -> estilos
* script.js -> lógica do frontend