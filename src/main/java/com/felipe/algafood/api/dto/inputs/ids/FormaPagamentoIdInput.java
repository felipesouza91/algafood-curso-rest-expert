package com.felipe.algafood.api.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@NotNull
	@ApiModelProperty(value = "Codigo de uma forma de pagamento", required = true , example = "5")
	private Long id;
}
