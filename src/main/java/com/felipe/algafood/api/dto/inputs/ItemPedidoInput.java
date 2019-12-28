package com.felipe.algafood.api.dto.inputs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	private Long produtoId;
	
	@NotNull
	@Positive(message = "Quantidade do iten deve ser mairo que zero")
	private Integer quantidade;
	
	private String observacao;
	
}
