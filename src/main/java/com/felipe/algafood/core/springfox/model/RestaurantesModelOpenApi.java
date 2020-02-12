package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.RestauranteBasicModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("RestaurantesModel")
public class RestaurantesModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("RestauranteEmbeddedModel")
	public class EmbeddedModelApi {
		private List<RestauranteBasicModel> restaurantes;
	}
}
