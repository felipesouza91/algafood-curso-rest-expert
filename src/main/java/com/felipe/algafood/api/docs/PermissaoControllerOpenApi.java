package com.felipe.algafood.api.docs;

import org.springframework.hateoas.CollectionModel;

import com.felipe.algafood.api.dto.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissoes")
public interface PermissaoControllerOpenApi {
	
	@ApiOperation("Listar Todas as permissoes")
	public CollectionModel<PermissaoModel> listarTodas();

}
