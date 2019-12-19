create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null
)engine=InnoDB default charset=utf8;
alter table restaurante_forma_pagamento add constraint fk_forma_de_pagamento_id foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento add constraint fk_restaurante foreign key (restaurante_id) references restaurante (id);