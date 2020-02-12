package com.felipe.algafood.api.v2.docs;

import org.springframework.hateoas.CollectionModel;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v2.dto.inputs.CidadeInputV2;
import com.felipe.algafood.api.v2.dto.model.CidadeModelV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {


	@ApiOperation("Listar as cidades")
	public CollectionModel<CidadeModelV2> buscar() ;
	
	@ApiOperation("Busca a cidade por Id")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da cidade invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public CidadeModelV2 buscarPorId( Long id) ;
	
	@ApiOperation("Inserir um nova cidade")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Cidade cadastrada")
	})
	public CidadeModelV2 salvar ( CidadeInputV2 cidadeInput);
	
	@ApiOperation("Atualizar uma cidade")
	@ApiResponses({
		@ApiResponse(code= 200, message = "Cidade Atualizada"),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public CidadeModelV2 atualizar( Long id,  CidadeInputV2 cidadeInput) ;
	
	@ApiOperation("Excluir uma cidade")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Cidade excluida"),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public void excluir( Long id) ;
}
