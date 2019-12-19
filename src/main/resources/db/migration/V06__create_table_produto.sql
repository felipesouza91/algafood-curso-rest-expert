create table produto (
	id bigint not null auto_increment,
	ativo bit,
	descricao varchar(255),
	nome varchar(255),
	preco decimal(19,2),
	restaurante_id bigint not null,
	primary key (id)
)engine=InnoDB default charset=utf8;
