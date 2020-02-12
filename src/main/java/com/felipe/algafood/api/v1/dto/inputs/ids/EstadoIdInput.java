package com.felipe.algafood.api.v1.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

	@NotNull
	@ApiModelProperty(value = "Codigo do estado",example = "1", required = true)
	private Long id;
}
