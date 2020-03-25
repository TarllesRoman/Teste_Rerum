CREATE DATABASE rerumapi;

CREATE TABLE bovino (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    brinco VARCHAR(8) NOT NULL,
    nome VARCHAR(8) NOT NULL,
    situacao VARCHAR(8) NOT NULL,
    sexofeminino BOOLEAN NOT NULL DEFAULT True,
    brincomae VARCHAR(8) NOT NULL,
    brincopai VARCHAR(8) NOT NULL,
    raca VARCHAR(15) NOT NULL,
    nascimento DATE,
    prenhes DATE,
    ultimoParto DATE,
)ENGINE=InnoDB DEFAULT CHARSET=utf8;