package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.EstadoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EstadosModel")
public class EstadosModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("EstadoEmbeddedModel")
	public class EmbeddedModelApi {
		private List<EstadoModel> estados;
	}
}
