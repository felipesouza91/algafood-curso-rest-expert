create table cidade(
	id bigint not null primary key auto_increment,
	nome_cidade varchar(80),
	nome_estado varchar(80)
)engine=InnoDB default charset=utf8;

insert into cidade (nome_cidade, nome_estado) values ('Rio de janeiro', 'Rio de janeiro');
insert into cidade (nome_cidade, nome_estado) values ('São Paulo', 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Belo Horizonte', 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('Ouro Petro', 'Minas Gerais');