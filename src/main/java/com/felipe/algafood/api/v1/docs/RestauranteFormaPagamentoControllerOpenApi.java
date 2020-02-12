package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista forma de pagamentos do restaurante")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id da resturante invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public CollectionModel<FormaPagamentoModel> listarTodos(@ApiParam(value = "Codigo de um restaurante", required = true) Long id);
	
	@ApiOperation("Associar forma de pagamento a Restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Forma de pagamento associada"),
		@ApiResponse(code= 400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Representação não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void>  associar(@ApiParam(value = "Codigo de um restaurante", required = true) Long id, 
			@ApiParam(value = "Codigo da forma de pagamento", required = true)Long idFormaPagamento) ;
	
	@ApiOperation("Desassociar forma de pagamento de Restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Forma de pagamento desassociar"),
		@ApiResponse(code= 400, message = "Codigo invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Representação não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void>  desassociar( @ApiParam(value = "Codigo de um restaurante", required = true) Long id, 
			@ApiParam(value = "Codigo da forma de pagamento", required = true)Long idFormaPagamento) ;
}
