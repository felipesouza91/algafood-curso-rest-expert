package com.felipe.algafood.api.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "formas-pagamentos")
public class FormaPagamentoModel  extends RepresentationModel<FormaPagamentoModel>{

	@ApiModelProperty(value = "Codigo da forma de pagamento" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Descrição da forma de pagamento" , example = "Cartão de credito")
	private String descricao;
}
