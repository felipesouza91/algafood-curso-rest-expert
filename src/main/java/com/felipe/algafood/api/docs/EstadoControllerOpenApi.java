package com.felipe.algafood.api.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.dto.inputs.EstadoInput;
import com.felipe.algafood.api.dto.model.EstadoModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Buscar Estados")
	public ResponseEntity<List<EstadoModel>> buscar();
	
	@ApiOperation("Buscar Estados por id")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da estado invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Estado não encontrada", response = Problem.class)
	})
	public ResponseEntity<EstadoModel> buscarPorId(@ApiParam(value = "Codigo de um estado", required = true)Long id);

	@ApiOperation("Cadastrar estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado criado")
	})
	public EstadoModel salvar (@ApiParam(name="Corpo", value = "Representação de estado" ,required = true)EstadoInput estadoInput);
	
	@ApiOperation("Atualizar estado")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code= 400, message = "Id da estado invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Estado não encontrada", response = Problem.class)
	})
	public ResponseEntity<EstadoModel> atualizar(@ApiParam(value = "Codigo de um estado", required = true)Long id,
			@ApiParam(name="Corpo", value = "Representação de estado" ,required = true) EstadoInput estadoInput);
	
	@ApiOperation("Remover Estado")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado removido"),
		@ApiResponse(code= 404, message = "Estado não encontrada", response = Problem.class)
	})
	public void excluir(@ApiParam(value = "Codigo de um estado", required = true) Long id) ;
}
