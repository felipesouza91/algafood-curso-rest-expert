package com.felipe.algafood.api.docs;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.dto.model.PermissaoModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation("Listar todos as permissoes de um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de grupo invalido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não foi encontrado", response = Problem.class)
	})
	public CollectionModel<PermissaoModel> listarTodos(@ApiParam(value = "Codigo de um grupo", required = true, example = "1") Long id) ;

	@ApiOperation("Associar uma permisão a um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void>  associar(@ApiParam(value = "Codigo de um grupo", required = true, example = "1") Long id,
			@ApiParam(value = "Codigo da permissão", required = true, example = "1") Long idPermissao) ;
	
	@ApiOperation("Desassociar uma permisão a um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void>  desassociar( @ApiParam(value = "Codigo de um grupo", required = true, example = "1") Long id, 
			@ApiParam(value = "Codigo da permissão", required = true, example = "1") Long idPermissao) ;
}
