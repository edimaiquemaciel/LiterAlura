# LiterAlura 📚

LiterAlura é uma aplicação Spring Boot que integra com a API Gutendex para gerenciar e catalogar livros e autores. O sistema oferece funcionalidades abrangentes para busca, armazenamento e análise de informações sobre livros, com foco especial no gerenciamento de autores e análise estatística.

## 🚀 Funcionalidades

- Busca de livros por título
- Listagem de livros e autores cadastrados
- Filtro de autores por ano de vida
- Filtro de livros por idioma
- Visualização dos 10 livros mais baixados
- Busca de autores por nome
- Visualização de estatísticas de download de livros
- Integração com API Gutendex para dados de livros
- Armazenamento persistente usando JPA/Hibernate
- Gerenciamento de relacionamento bidirecional entre livros e autores

## 📸 Screenshots e Exemplos de Uso

### Menu Principal
```
***** LiterAlura *****

Escolha o número de sua opção:

1- Buscar livro pelo título
2- Listar livros registrados
3- Listar autores registrados
4- Listar autores vivos em um determinado ano
5- Listar livros em um determinado idioma
6- Listar Top10 livros mais baixados
7- Buscar Autor por nome
8- Mostrar estatísticas de Livros
0- Sair
```

### 1. Busca de Livros
```
Insira o nome do livro que você deseja procurar:
Dom Casmurro

----- LIVRO -----
Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Número de Downloads: 1234
```

### 2. Lista de Livros Registrados
```
----- Livros Registrados -----
----- LIVRO -----
Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Número de Downloads: 1234

----- LIVRO -----
Título: O Alienista
Autor: Machado de Assis
Idioma: pt
Número de Downloads: 987
```

### 3. Lista de Autores
```
----- Autores Registrados -----
Autor: Machado de Assis
Ano de nascimento: 1839
Ano de falecimento: 1908
Livros: 1 - Dom Casmurro, 2 - O Alienista
```

### 4. Autores Vivos em um Ano
```
Insira o ano que deseja pesquisar:
1880

----- Autores registrados vivos em 1880 -----
Autor: Machado de Assis
Ano de nascimento: 1839
Ano de falecimento: 1908
Livros: 1 - Dom Casmurro, 2 - O Alienista
```

### 5. Livros por Idioma
```
Insira o idioma para realizar a busca
es - espanhol
en - inglês
fr - francês
pt - português

pt

----- LIVRO -----
Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Número de Downloads: 1234
```

### 6. Top 10 Livros Mais Baixados
```
----- Top 10 Livros Baixados -----
1. Dom Casmurro
2. O Alienista
3. Memórias Póstumas de Brás Cubas
...
```

### 7. Busca de Autor
```
Informe o nome do autor para busca:
Machado

Autor: Machado de Assis
Ano de nascimento: 1839
Ano de falecimento: 1908
Livros: 1 - Dom Casmurro, 2 - O Alienista
```

### 8. Estatísticas
```
----- Estatísticas de Banco de Dados -----
Mádia de downloads: 1105.5
Max downloads: 1234
Min downloads: 987
Livros Registrados: 2
```

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Jackson para processamento JSON
- Maven
- Banco de Dados H2 (configurável para outros bancos)
- Project Lombok

## 📋 Pré-requisitos

- Java Development Kit (JDK) 17 ou superior
- Maven 3.6.x ou superior
- Git para controle de versão

## 🗄️ Configuração do Banco de Dados

### Configuração Padrão 
 Para usar esta configuração, adicione as seguintes propriedades no arquivo `application.properties`:

### Configuração com PostgreSQL
Para utilizar PostgreSQL, adicione a dependência no `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.5.1</version>
</dependency>
```

Configure o `application.properties`:

```properties
# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Variáveis de Ambiente
Para maior segurança, recomenda-se usar variáveis de ambiente para informações sensíveis. Exemplo:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

## 🔧 Instalação

1. Clone o repositório
```bash
git clone https://github.com/edimaiquemaciel/literalura.git
```

2. Entre na pasta do projeto
```bash
cd literalura
```

3. Compile o projeto
```bash
mvn clean install
```

4. Execute a aplicação
```bash
mvn spring-boot:run
```

## 💻 Como Usar

A aplicação disponibiliza uma interface de linha de comando com as seguintes opções:

1. Buscar livro pelo título
2. Listar livros registrados
3. Listar autores registrados
4. Listar autores vivos em um determinado ano
5. Listar livros em um determinado idioma
6. Listar Top 10 livros mais baixados
7. Buscar autor por nome
8. Mostrar estatísticas de livros
0. Sair

## 🏗️ Estrutura do Projeto

```
br.com.alura.literalura
├── model
│   ├── Autor.java
│   ├── DadosAutor.java
│   ├── DadosLivros.java
│   └── Livro.java
├── repository
│   ├── AutorRepository.java
│   └── LivroRepository.java
├── service
│   ├── BookApiResponse.java
│   ├── ConsumoApi.java
│   ├── ConverteDados.java
│   └── IConverteDados.java
└── principal
    └── Principal.java
```

## 📝 Integração com API

A aplicação integra-se com a API Gutendex (https://gutendex.com/books/) para obter informações sobre livros. A integração inclui:

- Funcionalidade de busca de livros
- Recuperação de informações de autores
- Estatísticas de download
- Suporte a múltiplos idiomas

## 🔍 Consultas ao Banco de Dados

O projeto inclui diversas consultas personalizadas usando JPQL e SQL nativo:

```java
// Exemplo de consulta personalizada para encontrar autores vivos em um ano específico
@Query("SELECT a FROM Autor a WHERE " +
       "(a.anoNascimento <= :ano AND (a.anoFalecimento = 0 OR a.anoFalecimento > :ano))")
List<Autor> findAutoresVivosPorAno(@Param("ano") Integer ano);
```

## 🤝 Como Contribuir

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona uma nova feature'`)
4. Faça push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes

## ✨ Agradecimentos

- [API Gutendex](https://gutendex.com/) por fornecer o banco de dados de livros
- Alura pela inspiração e orientação do projeto
- Equipe Spring Boot pelo excelente framework

## 📬 Contato

Edimaique Maciel -  - edimaiqueacacio@gmail.com

---
⭐️ Desenvolvido por [Edimaique Maciel](https://github.com/edimaiquemaciel)
