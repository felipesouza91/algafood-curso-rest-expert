package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation("Listar grupos de um usuario")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuario invalida", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario não encontrado", response = Problem.class)
	})
	public CollectionModel<GrupoModel> listarTodos(@ApiParam(value = "Codigo de um usuario", required = true, example = "1") Long id);
	
	@ApiOperation("Associar grupo a um usuario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo associado a usuario  com sucesso"),
		@ApiResponse(code = 400, message = "Id do Usuario ou Grupo invalidas", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario ou Grupo não encontrado", response = Problem.class),
	})
	public ResponseEntity<Void> associarGrupo(@ApiParam(value = "Codigo de um usuario", required = true, example = "1") Long id, 
			@ApiParam(value = "Codigo de um grupo", required = true, example = "1") Long idGrupo);
	
	@ApiOperation("Associar grupo a um usuario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo desassociado a usuario  com sucesso"),
		@ApiResponse(code = 400, message = "Id do Usuario ou Grupo invalidas", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario ou Grupo não encontrado", response = Problem.class),
	})
	public ResponseEntity<Void> desassociarGrupo(@ApiParam(value = "Codigo de um usuario", required = true, example = "1") Long id,
			@ApiParam(value = "Codigo de um grupo", required = true, example = "1") Long idGrupo) ;
}
