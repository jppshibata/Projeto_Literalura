# Olá, Bem vindo ao meu projeto Literalura Feito em SpringData JPA
 
## Ferramentas utilizadas:

* Java

## Requisitos:
A aplicação foi desenvolvida e testada com a versão 17 do Java.

## Descrição da Aplicação:

### Conforme a proposta, de puxar uma quantidade de livros da api <a href="https://gutendex.com/">Gutendex API</a>, o qual possui as informações de diversos livros diferentes.
 * Id do Livro gerado automaticamente;
 * Título do Livro;
 * Lista de Autores envolvidos;
 * Número de Downloads.
 
 ### A aplicação de uma forma simplificada pega essas informações da API, e permite que o usuário escolha entre determinadas opções.
 
* 1- Buscar livro por título
* 2- Listar livros registrados
* 3- Listar autores registrados
* 4- Listar autores vivos em um determinado ano
* 5- Listar livros por idioma
* 6- Sair
    
### Adendo que foram utilizadas as Bibliotecas Jackson para que pudesse converter o Json fornecido da API para Objeto 
### Foram utilizados os dados dos livros e os dados de autor para cumprir as opções propostas pela aplicação, devidamente extraidos na api, através de um outro record de busca (SearchData), necessário porque os dados são retornados através de uma busca por nomes. 

### Apenas a primeira função utiliza a api para o registro de livros, as demais utilizam um banco de dados local (postgres/pgadmin) para operações de busca, como listar os livros o qual serão registrados, autores, e filtros como autores vivos em determinado ano e livros por idiomas disponíveis.

## Feito por:

### João Paulo Pagenotto Shibata

### Linkedin: https://www.linkedin.com/in/joao-paulo-pagenotto-shibata

```
