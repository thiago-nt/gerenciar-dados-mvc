-- -----------------------------------------------------
-- Table Pessoa
-- -----------------------------------------------------
CREATE TABLE pessoa (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    datanascimento DATE,
    cpf VARCHAR(14),
    funcionario BOOLEAN,
    gerente BOOLEAN,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table Projeto
-- -----------------------------------------------------
CREATE TABLE projeto (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE,
    data_previsao_fim DATE,
    data_fim DATE,
    descricao VARCHAR(5000),
    status VARCHAR(45),
    orcamento FLOAT,
    risco VARCHAR(45),
    idgerente BIGINT NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
        REFERENCES pessoa (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table Membros (Associação Projeto x Pessoa)
-- -----------------------------------------------------
CREATE TABLE membros (
    id BIGSERIAL NOT NULL,
    id_projeto BIGINT NOT NULL,
    id_pessoa BIGINT NOT NULL,
    papel VARCHAR(100),
    CONSTRAINT pk_membros PRIMARY KEY (id),
    CONSTRAINT fk_membro_projeto FOREIGN KEY (id_projeto)
        REFERENCES projeto (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_membro_pessoa FOREIGN KEY (id_pessoa)
        REFERENCES pessoa (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
