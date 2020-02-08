package com.felipe.algafood.api.docs;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.felipe.algafood.api.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.dto.model.RestauranteBasicModel;
import com.felipe.algafood.api.dto.model.RestauranteModel;
import com.felipe.algafood.api.dto.model.resumo.RestauranteApenasNomeModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation(value = "Lista resturante")
	@ApiImplicitParams({
		@ApiImplicitParam(value="Nome da projeção de pedidos", name="projecao", dataType ="string",
				paramType = "query",allowableValues = "apenas-nome")
	})
	public CollectionModel<RestauranteBasicModel> buscarTodos();
	
	@ApiIgnore
	@ApiOperation(value = "Lista resturante", hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> buscarTodosComNome();
	
	@ApiOperation(value = "Buscar restaurante por codigo")
	@ApiResponses({
		@ApiResponse(code= 400, message = "Id do restaurante invalido", response = Problem.class),
		@ApiResponse(code= 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public ResponseEntity<RestauranteModel> buscarPorId(@ApiParam(value = "Codigo de restaurante", required = true) Long id );
	
	@ApiOperation(value = "Cadastro de um restaurante")
	@ApiResponses({
		@ApiResponse(code= 201, message = "Restaurante cadastrado", response = Problem.class),
	})
	public RestauranteModel cadastrar(@ApiParam(name="Corpo", value = "Representação de um restaurante")RestauranteInput restauranteModel);

	@ApiOperation("Atualizar um restaurante")
	@ApiResponses({
		@ApiResponse(code= 200, message = "Restaurante Atualizada"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public RestauranteModel atualizar (@ApiParam(value = "Codigo de restaurante", required = true) Long id,
			@ApiParam(name="Corpo", value = "Representação de um restaurante") RestauranteInput restauranteInput);
	
	@ApiOperation("Ativar restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurante ativado"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> ativarRestaurante(@ApiParam(value = "Codigo de restaurante", required = true) Long id);
	
	@ApiOperation("Ativar restaurantes")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurante ativado"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> ativarMultiplos(@ApiParam(name="Corpo" ,value = "Lista de codigo de restaurante") List<Long> restaurantesIds);
	
	@ApiOperation("Inativar restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurante inativo"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> inativarRestaurante(@ApiParam(value = "Codigo de restaurante", required = true) Long id);
	
	@ApiOperation("Inativar restaurantes")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurantes inativos"),
		@ApiResponse(code= 404, message = "Restaurantes não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> inativarMultiplos(@ApiParam(name="Corpo" ,value = "Lista de codigo de restaurante") List<Long> restaurantesIds);
	
	@ApiOperation("Fechar restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurante fechado"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> fecharRestaurante(@ApiParam(value = "Codigo de restaurante", required = true)Long id);
	
	@ApiOperation("Abrir restaurante")
	@ApiResponses({
		@ApiResponse(code= 204, message = "Restaurante aberto"),
		@ApiResponse(code= 404, message = "Restaurante não encontrada", response = Problem.class)
	})
	public ResponseEntity<Void> abrirRestaurante(@ApiParam(value = "Codigo de restaurante", required = true)  Long id);
	
	
}
