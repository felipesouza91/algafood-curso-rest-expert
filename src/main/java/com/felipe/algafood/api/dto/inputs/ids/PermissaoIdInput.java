package com.felipe.algafood.api.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoIdInput {

	@NotNull
	@ApiModelProperty(value = "Codigo da permisao", example = "1", required = true)
	private Long id;
}
