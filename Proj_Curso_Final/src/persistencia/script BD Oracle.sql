DROP TABLE TBPEDIDO;
DROP TABLE TBUSUARIO;
DROP TABLE TBLIVRO;
DROP TABLE TBEDITORA;
DROP TABLE TBAUTOR;

CREATE TABLE TBUSUARIO (
	CODUSUARIO NUMBER(10) PRIMARY KEY,
	NOME VARCHAR(40) NOT NULL,
	GENERO VARCHAR(40) NOT NULL,
	NASCIMENTO DATE NOT NULL,
	ENDERECO VARCHAR(40) NOT NULL,
	EMAIL VARCHAR(40) NOT NULL,
	SENHA VARCHAR(40) NOT NULL,
	NIVEL VARCHAR(40) NOT NULL
);

CREATE TABLE TBEDITORA (
	CODEDITORA NUMBER(10) PRIMARY KEY,
	NOME_EDITORA VARCHAR(40) NOT NULL,
	ORIGEM_EDITORA VARCHAR(40) NOT NULL
);

CREATE TABLE TBAUTOR (
	CODAUTOR NUMBER(10) PRIMARY KEY,
	NOME_AUTOR VARCHAR(40) NOT NULL,
	ORIGEM_AUTOR VARCHAR(40) NOT NULL,
	NASCIMENTO DATE NOT NULL
);

CREATE TABLE TBLIVRO (
	CODLIVRO NUMBER(10) PRIMARY KEY,
	TITULO VARCHAR(40) NOT NULL,
	ANO NUMBER(10) NOT NULL,
	CATEGORIA VARCHAR(40) NOT NULL,
	PRECO NUMBER(10) NOT NULL,
	COD_EDITORA NUMBER(10) NOT NULL,
	COD_AUTOR NUMBER(10) NOT NULL,
	FOREIGN KEY(COD_EDITORA) REFERENCES TBEDITORA(CODEDITORA),
	FOREIGN KEY(COD_AUTOR) REFERENCES TBAUTOR(CODAUTOR)
);

CREATE TABLE TBPEDIDO (
	CODPEDIDO NUMBER(10) PRIMARY KEY,
	COD_USUARIO NUMBER(10) NOT NULL,
	COD_LIVRO NUMBER(10) NULL,
	FOREIGN KEY(COD_USUARIO) REFERENCES TBUSUARIO(CODUSUARIO),
	FOREIGN KEY(COD_LIVRO) REFERENCES TBLIVRO(CODLIVRO)
);


DROP SEQUENCE SEQPEDIDO;
DROP SEQUENCE SEQUSUARIO;
DROP SEQUENCE SEQLIVRO;
DROP SEQUENCE SEQAUTOR;
DROP SEQUENCE SEQEDITORA;

CREATE SEQUENCE SEQPEDIDO;
CREATE SEQUENCE SEQUSUARIO;
CREATE SEQUENCE SEQLIVRO;
CREATE SEQUENCE SEQAUTOR;
CREATE SEQUENCE SEQEDITORA;


column titulo format a10;
column ano format a10;
column categoria format a10;
column preco format a10;
column nome format a10;
column genero format a10;
column endereco format a10;
column email format a10;
column senha format a10;
column nivel format a10;

column titulo format a10;
column ano format a10;
column preco format a10;
column nome_autor format a10;
column origem_autor format a10;
column nascimento format a10;
column nome_editora format a10;
column origem_editora format a10;
