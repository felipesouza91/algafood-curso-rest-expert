package com.felipe.algafood.api.v1.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.inputs.PedidoInput;
import com.felipe.algafood.api.v1.dto.model.PedidoModel;
import com.felipe.algafood.api.v1.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@ApiOperation("Listar Pedidos")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedas para filtrar na resposta separados por virgula",name = "campos" ,
				dataTypeClass  = String.class, paramType = "query")
	})
	public PagedModel<PedidoResumoModel> listarTodos(@ApiParam(name = "Filtro", value = "Campos para serem aplicados filtros no pedido") PedidoFilter filter,
			Pageable pageable);

	@ApiOperation("Busca a pedido por codigo")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Código do pedido invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Cidade não encontrada", response = Problem.class)
	})
	public PedidoModel listarPorId(@ApiParam(value = "Codigo de um pedido", required = true) String codigo);
	
	@ApiOperation("Criar um pedido")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Pedido criado")
	})
	public PedidoModel salvar(@ApiParam(name="Corpo", value = "Representação de um pedido" , required = true) PedidoInput pedidoInput);
}
