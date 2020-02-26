create table restaurante (
	id bigint not null auto_increment,
	data_atualizacao datetime not null,
	data_cadastro datetime not null,
	endereco_bairro varchar(255),
	endereco_cep varchar(255),
	endereco_complemento varchar(255),
	endereco_logradouro varchar(255),
	endereco_numero varchar(255),
	nome varchar(255) not null,
	taxa_frete decimal(19,2) not null,
	cozinha_id bigint not null,
	endereco_cidade_id bigint,
	primary key (id)
)engine=InnoDB default charset=utf8;

alter table restaurante add constraint fk_cozinha_d_id foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint fk_cidade_d_id foreign key (endereco_cidade_id) references cidade (id);
alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);