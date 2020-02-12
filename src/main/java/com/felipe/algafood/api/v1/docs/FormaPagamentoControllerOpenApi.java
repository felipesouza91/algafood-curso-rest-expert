package com.felipe.algafood.api.v1.docs;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.felipe.algafood.api.exceptionhandler.Problem;
import com.felipe.algafood.api.v1.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.v1.dto.model.FormaPagamentoModel;
import com.felipe.algafood.core.springfox.model.FormasPagamentosModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas Pagamentos")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation(value = "Listar formas de pagamentos", response = FormasPagamentosModelOpenApi.class)
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listarTodos(ServletWebRequest request);
	
	@ApiOperation( value = "Listar forma de pagamento por id")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id de forma de pagamento invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public ResponseEntity<FormaPagamentoModel> buscarPorId(@ApiParam(value = "Id de uma forma de pagamento",required = true, example = "10" )Long id,
			ServletWebRequest request);
	
	@ApiOperation("Cadastrar forma de pagamento")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Forma de pagamento cadastrada")
	})
	public FormaPagamentoModel criar(@ApiParam(name="Corpo", value = "Respresentação de uma forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Atualizar forma de pagamento")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id de forma de pagamento invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public FormaPagamentoModel atualizar(@ApiParam(value = "Id de uma forma de pagamento",required = true, example = "10" ) Long id,
			@ApiParam(name="Corpo", value = "Respresentação de uma forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Excluir forma de pagamento")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Forma de pagamento excluida"),
		@ApiResponse(code= 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	public void remover(@ApiParam(value = "Id de uma forma de pagamento",required = true, example = "10")  Long id);
}
