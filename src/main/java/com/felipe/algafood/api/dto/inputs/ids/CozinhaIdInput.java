package com.felipe.algafood.api.dto.inputs.ids;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInput {

	@NotNull
	private Long id;
}
