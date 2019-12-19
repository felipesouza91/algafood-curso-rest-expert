create table grupo (
	id bigint not null auto_increment,
	nome varchar(80),
	primary key (id)
)engine=InnoDB default charset=utf8;

create table permissao (
	id bigint not null,
	descricao varchar(255) not null,
	nome varchar(255) not null,
	primary key (id)
)engine=InnoDB default charset=utf8;


create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null
)engine=InnoDB default charset=utf8;
alter table grupo_permissao add constraint fk_permisao_id foreign key (permissao_id) references permissao (id);
alter table grupo_permissao add constraint fk_grupo_id foreign key (grupo_id) references grupo (id);
