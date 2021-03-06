package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

	@ApiOperation("Listar todos os Responsaveis associados a restaurante")
	@ApiResponses({
		@ApiResponse(code=400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code=404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public CollectionModel<UsuarioModel> listarTodosUsuarios(@ApiParam(value = "Codigo de um restaurante", required = true) Long id) ;
	
	@ApiOperation("Associar responsavel ao restaurante")
	@ApiResponses({
		@ApiResponse(code=204, message = "Responsavel associado"),
		@ApiResponse(code=400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code=404, message = "Representação não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void> associarResponsavel(@ApiParam(value = "Codigo de um restaurante", required = true) Long id,
			@ApiParam(value = "Codigo de um usuario", required = true)Long idUsuario);
	
	@ApiOperation("Desassociar responsavel ao restaurante")
	@ApiResponses({
		@ApiResponse(code=204, message = "Responsavel desassociado"),
		@ApiResponse(code=400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code=404, message = "Representação não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void> desassociarResponsavel(@ApiParam(value = "Codigo de um restaurante", required = true) Long id,
			@ApiParam(value = "Codigo de um usuario", required = true)Long idUsuario);
}
