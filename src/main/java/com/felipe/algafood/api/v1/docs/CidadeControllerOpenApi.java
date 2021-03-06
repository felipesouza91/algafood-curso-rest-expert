package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.inputs.CidadeInput;
import com.felipe.algafood.api.v1.dto.model.CidadeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Listar as cidades")
	public CollectionModel<CidadeModel> buscar();
	
	@ApiOperation("Busca a cidade por Id")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da cidade invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public CidadeModel buscarPorId(@ApiParam(value="ID de uma cidade", example = "1") Long id) ;
	
	@ApiOperation("Inserir um nova cidade")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Cidade cadastrada")
	})
	public CidadeModel salvar (	@ApiParam(name="Corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput) ;
	
	@ApiOperation("Atualizar uma cidade")
	@ApiResponses({
		@ApiResponse(code= 200, message = "Cidade Atualizada"),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public CidadeModel atualizar( @ApiParam(value="ID de uma cidade", example = "1") Long id, 
			@ApiParam(name="Corpo", value = "Representação de uma cidade com os dados novos")CidadeInput cidadeInput) ;
	
	@ApiOperation("Excluir uma cidade")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Cidade excluida"),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public void excluir(@ApiParam(value="ID de uma cidade", example = "1", required = true) Long id) ;
}
