package com.felipe.algafood.core.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.felipe.algafood.api.v1.dto.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("UsuariosModel")
public class UsuariosModelOpenApi {

	private EmbeddedModelApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("UsuariosEmbedded")
	public class EmbeddedModelApi {
		
		private List<UsuarioModel> usuarios;
	}
}
