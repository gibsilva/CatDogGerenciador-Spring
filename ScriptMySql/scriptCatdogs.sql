create database catdog;
use catdog;

CREATE TABLE fornecedor (
  Id int NOT NULL auto_increment,
  Nome varchar(150) NOT NULL,
  RazaoSocial varchar(100) DEFAULT NULL,
  Documento varchar(14) NOT NULL,
  Ativo bit(1) NOT NULL,
  Cep varchar(8) NOT NULL,
  DataHoraCriacao datetime NOT NULL default now(),
  Estado varchar(2) NOT NULL,
  Cidade varchar(60) NOT NULL,
  Bairro varchar(60) NOT NULL,
  Logradouro varchar(80) NOT NULL,
  Numero varchar(15) NOT NULL,
  Complemento varchar(40) NOT NULL,
  PRIMARY KEY (Id)
  );

  CREATE TABLE categoria (
  Id int NOT NULL AUTO_INCREMENT,
  Nome varchar(50) NOT NULL,
  DataHoraCriacao datetime NOT NULL DEFAULT now(),
  PRIMARY KEY (Id)
  );
  
  CREATE TABLE raca(
  Id int NOT NULL auto_increment,
  Nome varchar (100) NOT NULL,
  DataHoraCriacao datetime NOT NULL default now(),
  PRIMARY KEY (Id)
  );
  
  CREATE TABLE produto (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(100) NOT NULL,
  Descricao varchar(100) NOT NULL,
  Especificacao varchar(300) NOT NULL,
  PrecoCompra decimal(18,2) NOT NULL,
  PrecoVenda decimal(18,2) NOT NULL,
  Quantidade int(11) NOT NULL,
  Ativo bit(1) NOT NULL,
  IdCategoria int(11) NOT NULL,
  IdFornecedor int(11) NOT NULL,
  TipoAnimal int(11) DEFAULT NULL,
  DataHoraCriacao datetime NOT NULL DEFAULT now(),
  PorteAnimal int(11) NOT NULL,
  PRIMARY KEY (Id),
  KEY IX_Produto_IdCategoria (IdCategoria),
  KEY IX_Produto_IdFornecedor (IdFornecedor),
  CONSTRAINT FK_Produto_Categoria_IdCategoria FOREIGN KEY (IdCategoria) REFERENCES categoria (Id) ON DELETE RESTRICT,
  CONSTRAINT FK_Produto_Fornecedor_IdFornecedor FOREIGN KEY (IdFornecedor) REFERENCES fornecedor (Id) ON DELETE RESTRICT
);
  
  CREATE TABLE imagem (
  Id int  NOT NULL auto_increment,
  IdProduto int NOT NULL,
  Nome varchar(150) NOT NULL,
  Caminho varchar(20) NOT NULL,
  Tipo varchar(100) DEFAULT NULL,
  DataHoraCriacao datetime NOT NULL default now(),
  Arquivo mediumblob,
  PRIMARY KEY (Id),
  KEY IX_Imagem_IdProduto (IdProduto),
  CONSTRAINT FK_Imagem_Produto_IdProduto 
  FOREIGN KEY (IdProduto) 
  REFERENCES produto (Id) ON DELETE RESTRICT
  );
  
CREATE TABLE usuario (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(80) NOT NULL,
  Cpf varchar(11) NOT NULL,
  Email varchar(80) NOT NULL,
  DataNasc datetime,
  Permissao int(11) NOT NULL,
  Senha varchar(60) NOT NULL,
  DataHoraCriacao datetime NOT NULL DEFAULT now(),
  PRIMARY KEY (Id),
  UNIQUE KEY Cpf (Cpf),
  UNIQUE KEY Email (Email)
);

CREATE TABLE cliente (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(80) NOT NULL,
  Cpf varchar(11) NOT NULL,
  Email varchar(80) NOT NULL,
  DataNasc datetime NOT NULL,
  Login varchar(20) NOT NULL,
  Permissao int(11) NOT NULL,
  Senha varchar(16) NOT NULL,
  Cep varchar(8) DEFAULT NULL,
  DataHoraCriacao datetime NOT NULL DEFAULT now(),
  PRIMARY KEY (Id),
  UNIQUE KEY Cpf (Cpf),
  UNIQUE KEY Email (Email)
  );
