package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.dto.model.GrupoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GruposModel")
public class GruposModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;

	@Data
	@ApiModel("GruposEmbeddedModel")
	public class EmbeddedModelApi {
		private List<GrupoModel> grupos;
	}
}
