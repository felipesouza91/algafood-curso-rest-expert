
create table usuario (
	id bigint not null auto_increment,
	data_cadastro datetime(6),
	email varchar(255),
	nome varchar(255),
	senha varchar(255),
	primary key (id)
)engine=InnoDB default charset=utf8;

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null
)engine=InnoDB default charset=utf8;

alter table usuario_grupo add constraint fk_grupo_id_usuarios_grupos foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint fk_usuario_id_usuarios_grupos foreign key (usuario_id) references usuario (id);
