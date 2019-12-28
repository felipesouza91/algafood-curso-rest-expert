create table restaurante_usuario_responsavel (
	restaurante_id bigint not null,
	usuario_id bigint not null
)engine=InnoDB default charset=utf8;
alter table restaurante_usuario_responsavel add constraint fk_usuario_id foreign key (usuario_id ) references usuario (id);
alter table restaurante_usuario_responsavel add constraint fk_restaurante_responsavel_id foreign key (restaurante_id) references restaurante (id);