create table itens_pedido (
	id bigint not null auto_increment primary key,
	sub_total decimal(19,2) not null,
	quantidade bigint not null,
	preco_unitario decimal(19,2) not null,
	preco_total decimal(19,2) not null,
	observacao varchar(255),
	produto_id bigint(20) not null,
	pedido_id bigint(20) not null,
	FOREIGN KEY (pedido_id) REFERENCES pedido(id),
	FOREIGN KEY (produto_id) REFERENCES produto(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;