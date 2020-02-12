package com.felipe.algafood.api.v1.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdInput {

	@NotNull
	@ApiModelProperty(value = "Codigo de um restaurante", required = true,example = "1")
	private Long id;
}
