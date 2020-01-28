package com.felipe.algafood.api.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

	@ApiModelProperty(value = "Codigo da forma de pagamento" , example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Descrição da forma de pagamento" , example = "Cartão de credito")
	private String descricao;
}
