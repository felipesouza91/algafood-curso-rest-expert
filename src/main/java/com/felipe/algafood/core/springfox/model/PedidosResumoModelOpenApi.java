package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.resumo.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi  {
	
	private EmbeddedModelApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@Data
	@ApiModel("PedidosEmbeddedModel")
	public class EmbeddedModelApi {
		private List<PedidoResumoModel> pedidos;
	}
}
