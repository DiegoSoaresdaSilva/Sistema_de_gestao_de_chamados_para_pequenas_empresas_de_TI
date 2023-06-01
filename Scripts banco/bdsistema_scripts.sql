show databases;
use bdsistema;
-- Banco de dados do projeto de sistema de chamados para pequenas empresas de TI
CREATE DATABASE bdsistema;
USE bdsistema;
CREATE TABLE tanalistas (
   idanalista int primary key auto_increment,
   analista varchar (60) not null, 
   email varchar (50), 
   endereco varchar (100),
   bairro varchar (50),
   cidade varchar (50),
   estado varchar (50),
   telefone varchar (20) not null,
   login varchar(30) not null unique, 
   senha varchar (50) not null 
); 
DESCRIBE tanalistas;
INSERT INTO tanalistas (analista,email,endereco,bairro,cidade,estado, telefone,login,senha)
VALUES ('Diego','diego@teste.com.br','Rua X - Nº 1' ,'Bairro VWXYZ','','RS','9999-9999','Diego','123456'); 
UPDATE tanalistas set login ='diego' where idanalista=1;
use bdsistema;
INSERT INTO tanalistas (analista,email,endereco,bairro,cidade,estado, telefone,login,senha)
VALUES ('administrador','teste@teste','Rua A - Nº 100' ,'Bairro B','Guaíba','RS','9999-9999','administrador','123456'); 
INSERT INTO tanalistas (analista,email,endereco,bairro,cidade,estado, telefone,login,senha)
VALUES ('analista','teste@teste','Rua ABC - Nº 10', 'Bairro Y','Porto Alegre','RS', '9999-9999','analista','123456');
SELECT *FROM tanalistas;

CREATE TABLE tclientes(
   idclientes int primary key auto_increment,
   nomecliente varchar (100) not null,
   email varchar (50),
   endereco varchar (100),
   cidade varchar (50),
   estado varchar (50),
   telefone varchar (50) not null);

DESCRIBE tclientes;
ALTER TABLE tclientes ADD bairro varchar (50) after endereco;
SELECT *FROM tclientes;
INSERT INTO tclientes (nomecliente,email,endereco,bairro,cidade,estado,telefone)
VALUES ('Contabilidade A','teste@teste','Rua ABC - Nº 200','Bairro bloco A','Guaíba','RS','9999-9999');
SELECT *FROM tclientes;

CREATE TABLE tchamado (
   idchamado int primary key auto_increment,
   -- gerar data e hora automatizados atraves do servidor e coloca na variável dataehora
   dataehora timestamp default current_timestamp,
   chamado varchar (100) not null, -- titulo e breve informações sobre o chamado
   descricaochamado varchar (300) not null,
   resolucaochamado varchar (600) not null,
   analista varchar (30),
   valor decimal (10,2),
   -- vinculo de cliente e chamado - chave estrangeira idclientes da tabela tclientes dentro da tabela tchamado
   idclientes int not null,
   foreign key (idclientes) references tclientes(idclientes)
);

INSERT INTO tchamado (chamado,descricaochamado,resolucaochamado,analista,valor,idclientes)
VALUES ('Estudo de melhoria','cliente solicita estudo de melhoria para os equipamentos de informática','estudo realizado e enviado ao cliente','Diego',300.00,1);
SELECT *FROM tchamado;
SELECT* FROM tclientes;
INSERT INTO tchamado (chamado,descricaochamado,resolucaochamado,analista,valor,idclientes)
VALUES ('Computador sem acesso à Internet','cliente solicita verificação de máquina da recepção','Em análise','',100,1);
SELECT *FROM tclientes;
USE bdsistema;
SELECT *FROM tanalistas;
SELECT *FROM tchamado;
show databases;
SELECT *FROM tanalistas;
use bdsistema;
show databases;
SELECT *FROM tclientes ;
SELECT *FROM tclientes where nomecliente = "contabilidade A";
SELECT *FROM tchamado where resolucaochamado = "estudo realizado e enviado ao cliente";
DESCRIBE tanalistas;
ALTER TABLE tanalistas ADD COLUMN permissao VARCHAR (30) NOT NULL;
UPDATE tanalistas set permissao ='administrador' where idanalista = 1;
UPDATE tanalistas set permissao ='administrador' where idanalista = 2;
SELECT *FROM tanalistas;
UPDATE tanalistas set permissao ='usuario' where idanalista = 3;
SELECT *FROM tchamado ;
UPDATE tanalistas set analista = "diego" where idanalista = 1;
use bdsistema;
DESCRIBE tanalistas;
DESCRIBE tclientes;
ALTER TABLE tanalistas ADD permissao varchar (10);
SELECT *FROM tanalistas;
ALTER TABLE tanalistas DROP permissao;
ALTER TABLE tanalistas ADD permissao varchar (15);
UPDATE tanalistas set permissao = 'administrador' where idanalista = 1;
UPDATE tanalistas set permissao = 'administrador' where idanalista = 2;
UPDATE tanalistas set permissao = 'usuario' where idanalista = 3;
SELECT *FROM tanalistas;
SELECT *FROM tanalistas where idanalista = 3;
UPDATE tanalistas set analista = 'Analista' where idanalista = 3;
UPDATE tanalistas set bairro = 'Bairro Vwxyz' where idanalista = 1;
UPDATE tanalistas set bairro = 'Bairro Abc' where idanalista = 3;
SELECT *FROM tanalistas;
UPDATE tanalistas set endereco = 'Rua A - Nº 11' where idanalista = 3;
UPDATE tanalistas set analista = 'Diego' where idanalista = 1;
UPDATE tanalistas set analista = 'Ana Silva' where idanalista = 3;
UPDATE tanalistas set login = 'Ana.silva' where idanalista = 3;
INSERT INTO tanalistas (analista,email,telefone,login,senha)
VALUES ('Joao lima','joao@teste','9999-8888','joao.lima','1234');
DELETE FROM tanalistas where idanalista = 4;
ALTER TABLE tanalistas ADD COLUMN permissao varchar (15) not null;
UPDATE tanalistas set permissao = 'Administrador' where idanalista = 1;
UPDATE tanalistas set permissao = 'Usuario' where idanalista = 3;
UPDATE tanalistas set permissao = 'Administrador' where idanalista = 2;
SHOW DATABASES;
USE bdsistema;
SELECT *FROM tanalistas;
UPDATE tanalistas set analista = "Ana Silva" where idanalista = 3;
DELETE FROM tanalistas where idanalista = 3;
DESCRIBE tanalistas;
SELECT *FROM tanalistas;
DELETE FROM tanalistas where idanalista = 4;
DELETE FROM tanalistas where idanalista = 5;
DELETE FROM tanalistas where idanalista = 6;
DESCRIBE tclientes;
SELECT *FROM tanalistas;
SELECT *FROM tclientes;
DESCRIBE tchamado;
ALTER TABLE tchamado ADD resolucao varchar (255) after descricao;
USE bdsistema;
UPDATE tchamado SET DESCRICAO = 'Cliente solicita estudo de melhoria na infraestrutura de Rede' WHERE idchamado=1;
UPDATE tchamado SET DESCRICAO = 'Cliente sem acesso à Internet nos computadores da recepção' WHERE idchamado=2;
USE bdsistema;
DESCRIBE tchamado;
SELECT *FROM tanalistas;
SELECT *FROM tclientes;
SELECT *FROM tchamado;

SELECT *FROM tclientes ORDER BY nomecliente;

-- relatório dos chamados abertos:
SELECT 
CHA.idchamado,dataehora,status,chamado,descricao, analista,
CLI.nomecliente,telefone
FROM tchamado as CHA
INNER JOIN tclientes as CLI
ON ((CLI.idclientes = CHA.idclientes))
WHERE status != 'concluído';

-- relatório dos chamados concluídos:
SELECT 
CHA.idchamado,dataehora,status,chamado,descricao,resolucao,analista,valor,
CLI.nomecliente,telefone
FROM tchamado as CHA
INNER JOIN tclientes as CLI
ON ((CLI.idclientes = CHA.idclientes))
WHERE status = 'concluído';

-- relatório completo com todos os chamados:
SELECT 
CHA.idchamado,dataehora,status,chamado,descricao,resolucao,analista,valor,
CLI.nomecliente,telefone
FROM tchamado as CHA
INNER JOIN tclientes as CLI
ON ((CLI.idclientes = CHA.idclientes));

DESCRIBE tchamado;
ALTER TABLE tchamado MODIFY descricao LONGTEXT NOT NULL;
ALTER TABLE tchamado MODIFY resolucao LONGTEXT;
USE bdsistema;
SELECT * FROM tclientes;
DESCRIBE tchamado;

USE bdsistema;
SELECT * FROM tchamado;
UPDATE tchamado set status = 'Em atendimento' where idchamado = 60;

-- relatório dos valores somados:
SELECT SUM(valor) AS total,CHA.idchamado,dataehora,chamado,analista,
CLI.nomecliente,telefone
FROM tchamado as CHA
INNER JOIN tclientes as CLI
ON ((CLI.idclientes = CHA.idclientes))
WHERE status = 'Concluído'
GROUP BY CLI.nomecliente;

SELECT SUM(valor) AS total 
FROM tchamado
WHERE status = 'Concluído';
-- -------------------------------
show tables;
SELECT * FROM tchamado;
USE bdsistema;
DESCRIBE tclientes;
DESCRIBE tanalistas;
SELECT * FROM tanalistas;
SELECT * FROM tchamado;
DESCRIBE tchamado;
USE bdsistema;
SELECT *FROM tanalistas;
SELECT *FROM tclientes;
DESCRIBE tclientes;
SELECT *FROM tanalistas WHERE idnalista = 1;
UPDATE tanalistas set email = "diego@teste.com" where idanalista = 1;
UPDATE tanalistas set login = "diego" where idanalista = 1;










