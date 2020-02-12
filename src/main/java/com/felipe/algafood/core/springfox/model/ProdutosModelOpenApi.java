package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("ProdutosModel")
public class ProdutosModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("ProdutosEmbedded")
	public class EmbeddedModelApi {
		private List<ProdutoModel> produtos;
	}
}
