package com.felipe.algafood.api.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoIdInput {

	@NotNull
	private Long id;
}
