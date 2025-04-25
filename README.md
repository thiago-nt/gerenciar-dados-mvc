# 📊 Sistema para gerenciar dados - Full Stack com JSP/Bootstrap + Spring Boot + PostgreSQL

Sistema para gerenciar os dados do portfólio de projetos de uma empresa, utilizando Jsp/BootStrap no front-end e Spring Boot no back-end com persistência no banco PostgreSQL.

---

## 🐳 Subindo Docker Compose com o BD Postgres
### Passos para executar:

1. Acesse o diretório docker dentro do resources do projeto `empresa-portifolio` e execute:
   docker-compose up -d

#### Acesse o front-end em:
http://localhost:8080/api/projetos

#### Acesso a Api de associacao de membros via Postman/Insomnia:
curl --request POST \
--url http://localhost:8080/api/membros/associacao \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/11.0.2' \
--data '{
"idProjeto": 10,
"idPessoa": 1,
"papel": "Desenvolvedor Back-End"
}'


#### Acesso ao Banco de Dados
- URL: jdbc:postgresql://localhost:5432/empresa_db
- Usuário: postgres
- Senha: root

## Front-end
Tecnologias utilizadas:
- Jstl 2.0.0
- Bootstrap 5.3.0

## Back-end
Tecnologias utilizadas:
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Lombok
- PostgreSQL 15
- Mockito para testes unitários

🧪 TDD
- Back-end:  Testes unitários utilizando JUnit e Mockito