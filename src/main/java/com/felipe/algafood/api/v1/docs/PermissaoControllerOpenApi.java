package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;

import com.felipe.algafood.api.v1.dto.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissoes")
public interface PermissaoControllerOpenApi {
	
	@ApiOperation("Listar Todas as permissoes")
	public CollectionModel<PermissaoModel> listarTodas();

}
