package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.dto.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@Data
	@ApiModel("CozinhasEmbeddedModel")
	public class EmbeddedModelApi {
		private List<CozinhaModel> cozinhas;
	}
}
