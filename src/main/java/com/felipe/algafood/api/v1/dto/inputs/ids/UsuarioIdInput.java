package com.felipe.algafood.api.v1.dto.inputs.ids;

import lombok.Setter;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
@Setter
public class UsuarioIdInput {

	@NotNull
	private Long id;
}
