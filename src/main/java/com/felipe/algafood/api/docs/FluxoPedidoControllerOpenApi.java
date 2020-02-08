package com.felipe.algafood.api.docs;

import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirmação de um pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido confirmado"),
		@ApiResponse(code= 404, message = "Pedido com o codigo não foi encontrado",response = Problem.class)
	})
	public ResponseEntity<Void> confirmar(@ApiParam(value = "Codigo do pedido", required = true) String codigo );
	
	@ApiOperation("Colocar um pedido para entrega")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido colocado em entrega" ),
		@ApiResponse(code= 404, message = "Pedido com o codigo não foi encontrado",response = Problem.class)
	})
	public ResponseEntity<Void> entregar(@ApiParam(value = "Codigo do pedido", required = true) String codigo ) ;
	
	@ApiOperation("Cancelar um pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido cancelado"),
		@ApiResponse(code= 404, message = "Pedido com o codigo não foi encontrado",response = Problem.class)
	})
	public ResponseEntity<Void> cancelar(@ApiParam(value = "Codigo do pedido", required = true) String codigo ) ;
}
