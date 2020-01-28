package com.felipe.algafood.api.dto.inputs;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.felipe.algafood.api.dto.inputs.ids.FormaPagamentoIdInput;
import com.felipe.algafood.api.dto.inputs.ids.RestauranteIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação de um restaurante", required = true)
	private RestauranteIdInput restaurante;

	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação de forma de pagamento", required = true)
	private FormaPagamentoIdInput formaPagamento;
	
	//@Valid
	//@NotNull
	//private UsuarioIdInput cliente;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "Representação de endereço de entrega", required = true)
	private EnderecoInput enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min=1)
	@ApiModelProperty(value = "Representação dos itens do pedido", required = true)
	private List<ItemPedidoInput> itens;
}
