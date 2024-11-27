CREATE DATABASE trabalho;

USE trabalho;

CREATE TABLE equipamentos ( 
    idEq INT AUTO_INCREMENT NOT NULL,
    nomeEq VARCHAR(100),
    tipoEq VARCHAR(100),
    statusEq VARCHAR(100),
    modeloEq VARCHAR(100),
    qntEq INT,
    dataAquisicao date,
    PRIMARY KEY(idEq)
);

CREATE TABLE manutencao(
    idMan INT AUTO_INCREMENT NOT NULL,
    tipoMan VARCHAR(100),
    statusMan VARCHAR(100),
    eqMan VARCHAR(100),
    custoMan FLOAT,
    dataManutencao date,
    PRIMARY KEY(idMan)
);

