package com.felipe.algafood.api.docs;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.felipe.algafood.api.dto.inputs.FotoProdutoInput;
import com.felipe.algafood.api.dto.model.FotoProdutoModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFotoProdutoControllerOpenApi {

	@ApiOperation(value = "Buscar foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante ou produto invalido", response = Problem.class),
		@ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
	})
	public FotoProdutoModel buscarFoto(@ApiParam(value = "Codigo do restaurante", required = true,example = "1") Long restId,
			@ApiParam(value = "Codigo do produto", required = true,example = "1") Long prodId);
	
	
	@ApiOperation(value = "Buscar foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto( Long restId, Long prodId,
		 	 String acceptHeader) throws HttpMediaTypeNotAcceptableException ;

	@ApiOperation("Atualizar foto de um produto")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Foto do produto atualizada"),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
	})
	public FotoProdutoModel atualizaFoto(@ApiParam(value = "Codigo do restaurante", required = true ,example = "1") Long restId, 
			@ApiParam(value = "Codigo do produto", required = true ,example = "1") Long prodId,
			@ApiParam( value = "Representação de uma foto", required = true ) FotoProdutoInput produtoInput , 
			@ApiParam(value = "Arquivo da foto deve conter 500KB e ser do tipo JPEG ou PNG", required = true) MultipartFile arquivo ) throws IOException ;
	
	@ApiOperation("Remover foto de um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Foto do produto excluida"),
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
	})
	public void deletar(@ApiParam(value = "Codigo do produto", required = true, example = "1") Long restId, 
			@ApiParam( value = "Representação de uma foto", required = true, example = "1" ) Long prodId) ;
}
