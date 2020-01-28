package com.felipe.algafood.api.dto.inputs;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.felipe.algafood.api.dto.inputs.ids.PermissaoIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@NotBlank
	@ApiModelProperty(value = "Nome do grupo", example = "Administrador", required = true)
	private String nome;
	
	@Valid
	@ApiModelProperty(value = "Lista de permições", required = true, example = "")
	private List<PermissaoIdInput> permissoes;
}
