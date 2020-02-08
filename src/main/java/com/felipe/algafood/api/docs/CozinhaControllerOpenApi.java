package com.felipe.algafood.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.dto.model.CozinhaModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Listar cozinhas")
	public PagedModel<CozinhaModel> listar(Pageable pageable);
	
	@ApiOperation("Listar uma cozinha")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da cozinha invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel buscarPorId(@ApiParam(value = "Codigo de uma cozinha", example = "1") Long id);
	
	@ApiOperation("Cadastrar uma nova cozinha")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Cozinha cadastrada")
	})
	public CozinhaModel savar (@ApiParam(name = "Corpo", value = "Representação de uma nova cozinha")CozinhaInput cozinhaInput) ;
	
	@ApiOperation("Atualizar uma cozinha")
	@ApiResponses({
		@ApiResponse(code= 200, message = "Cozinha Atualizada"),
		@ApiResponse(code= 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public ResponseEntity<CozinhaModel> atualizar(@ApiParam(value = "Codigo de uma cozinha", example = "1")Long id,
			@ApiParam(name = "Corpo", value = "Representação de uma nova cozinha") CozinhaInput cozinhaInput);
	
	@ApiOperation("Excluir uma cozinha")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Cozinha excluida com sucesso"),
		@ApiResponse(code= 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public void remover(@ApiParam(value = "Codigo de uma cozinha", example = "1")Long id);
}
