package com.felipe.algafood.api.docs;

import java.util.List;

import com.felipe.algafood.api.dto.inputs.ProdutoInput;
import com.felipe.algafood.api.dto.model.ProdutoModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation("Listar produdos de restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Indica se deve incluir produtos inativos", dataType = "boolean", paramType = "query", 
				defaultValue = "false", name = "incluirInativo")
	})
	public List<ProdutoModel> buscarTodos(@ApiParam(value = "Codigo do restaurante", required = true) Long idRestaurante, boolean incluirInativo) ;
	
	@ApiOperation("Listar um produto de restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code = 404, message = "Representação não encontrado", response = Problem.class)
	})
	public ProdutoModel buscarPorId(@ApiParam(value = "Codigo do restaurante", required = true) Long idRestaurante,
			@ApiParam(value = "Codigo do produto", required = true)Long idProduto) ;
	
	@ApiOperation("Salvar um novo produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public ProdutoModel salvar(@ApiParam(value = "Codigo do restaurante", required = true) Long idRestaurante,
			@ApiParam(value = "Representação de um produto", required = true, name = "Corpo")ProdutoInput produtoInput);
	
	@ApiOperation("Atualizar um produto")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto atualizado"),
		@ApiResponse(code = 404, message = "Representação não encontrado", response = Problem.class)
	})
	public ProdutoModel atualizar(@ApiParam(value = "Codigo do restaurante", required = true) Long idRestaurante,
			@ApiParam(value = "Codigo do produto", required = true)Long idProduto,
			@ApiParam(value = "Representação de um produto", required = true, name = "Corpo") ProdutoInput produtoInput) ;
}
