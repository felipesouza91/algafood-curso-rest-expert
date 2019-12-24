package com.felipe.algafood.api.dto.inputs;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.felipe.algafood.api.dto.inputs.ids.PermissaoIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@NotBlank
	private String nome;
	
	@Valid
	private List<PermissaoIdInput> permissoes;
}
