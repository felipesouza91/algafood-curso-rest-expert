package com.felipe.algafood.api.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.model.CidadeModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControlerOpenApi {
	
	@ApiOperation("Listar as cidades")
	public ResponseEntity<List<CidadeModel>> buscar();
	
	@ApiOperation("Busca a cidade por Id")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da cidade invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public ResponseEntity<CidadeModel> buscarPorId(@ApiParam(value="ID de uma cidade", example = "1") Long id) ;
	
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
	public void excluir(@ApiParam(value="ID de uma cidade", example = "1") Long id) ;
}
