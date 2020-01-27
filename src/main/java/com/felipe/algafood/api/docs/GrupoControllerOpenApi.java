package com.felipe.algafood.api.docs;

import java.util.List;

import com.felipe.algafood.api.dto.inputs.GrupoInput;
import com.felipe.algafood.api.dto.model.GrupoModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Listar Grupos")
	public List<GrupoModel> listarTodos() ;
	
	@ApiOperation("Listar grupo pelo codigo")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id do grupo invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoModel listaPorId(@ApiParam(value = "Codigo do grupo", example = "1") Long id);
	
	@ApiOperation("Criar um novo grupo")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Grupo cadastrado")
	})
	public GrupoModel salvar(@ApiParam(name = "Corpo", value = "Respresentação de grupo") GrupoInput grupoInput) ;
	
	@ApiOperation("Atualizar um grupo")
	@ApiResponses({
		@ApiResponse(code= 200, message = "Grupo Atualizado"),
		@ApiResponse(code= 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoModel atualizar(@ApiParam(value = "Codigo do grupo", example = "1")Long id,
			@ApiParam(name="Corpo",value = "Respresentação de um novo grupo") GrupoInput grupoInput) ;
	
	@ApiOperation("Remover um grupo")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Grupo removido"),
		@ApiResponse(code= 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public void excluir(@ApiParam(value ="Id de um grupo", example = "1") Long id);
}
