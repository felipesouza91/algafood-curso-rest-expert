package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.dto.model.PermissaoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PermissoesModel")
public class PermissoesModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("PermissoesEmbeddedModel")
	public class EmbeddedModelApi {
		private List<PermissaoModel> permissoes;
	}
}
