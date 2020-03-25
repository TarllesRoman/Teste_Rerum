CREATE TABLE bovino (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    brinco VARCHAR(8) NOT NULL,
    nome VARCHAR(8) NOT NULL,
    situacao VARCHAR(8) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    brinco_mae VARCHAR(8),
    brinco_pai VARCHAR(8),
    raca VARCHAR(15) NOT NULL,
    nascimento DATE NOT NULL,
    prenhes DATE,
    ultimo_parto DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO bovino (brinco, nome, situacao, brinco_mae, brinco_pai, raca, nascimento, prenhes, ultimo_parto, sexo)
            VALUES ("12E3","Mimosa","Seca", "","","Holandês","2017-9-8","2019-9-12","2019-9-11", "F");

INSERT INTO bovino (brinco, nome, situacao, brinco_mae, brinco_pai, raca, nascimento, sexo)
            VALUES ("45E6","Trovão","Seca", "","","Holandês","2017-5-6", "M");

INSERT INTO bovino (brinco, nome, situacao, brinco_mae, brinco_pai, raca, nascimento, sexo)
            VALUES ("78E9","Estrela","Seca", "12E3","45E6","Holandês","2019-9-11", "F");            