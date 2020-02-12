package com.felipe.algafood.api.v1.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {

	@NotNull
	@ApiModelProperty(value = "Codigo da cidade", example = "2", required = true)
	private Long id;
}
