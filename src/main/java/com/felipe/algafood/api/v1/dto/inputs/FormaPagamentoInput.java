package com.felipe.algafood.api.v1.dto.inputs;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

	@NotBlank
	@ApiModelProperty(value = "nome da forma de pagamento", example = "Cartão de credito", required = true)
	private String descricao;
}
