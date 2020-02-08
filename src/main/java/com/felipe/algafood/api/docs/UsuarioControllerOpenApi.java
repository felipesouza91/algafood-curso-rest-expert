package com.felipe.algafood.api.docs;

import org.springframework.hateoas.CollectionModel;

import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.inputs.UsuarioSenhaInput;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Listar usuarios")
	public CollectionModel<UsuarioModel> listarTodos();
	
	@ApiOperation("Listar usuario por codigo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de usuario invalido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario não encontrado", response = Problem.class),
	})
	public UsuarioModel listarPorCodigo(@ApiParam(value = "Codigo do usuario", required = true, example = "1") Long id) ;
	
	@ApiOperation("Cadastra um usuario")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuario cadastrado com sucesso")
	})
	public UsuarioModel salvar(@ApiParam(value = "Representação de um usuario", required = true) UsuarioInput usuarioInput) ;
	
	@ApiOperation("Atualizar um usuario")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuario atualizado"),
		@ApiResponse(code = 404, message = "Usuario não encontrado", response = Problem.class),
	})
	public UsuarioModel atualizar(@ApiParam(value = "Codigo do usuario", required = true, example = "1") Long id,  
			@ApiParam(value = "Representação de um usuario", required = true) UsuarioInputNoPassword usuarioinput) ;
	
	@ApiOperation("Atualizar um senha de um usuario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Senha atualizado"),
		@ApiResponse(code = 404, message = "Usuario não encontrado", response = Problem.class),
	})
	public void alterarSenha(@ApiParam(value = "Codigo do usuario", required = true, example = "1") Long id,
			@ApiParam(value = "Representação da nova senha", required = true) UsuarioSenhaInput input);
	
	@ApiOperation("Excluir um usuario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuario excluido"),
		@ApiResponse(code = 404, message = "Usuario não encontrado", response = Problem.class),
	})
	public void excluir(@ApiParam(value = "Codigo do usuario", required = true, example = "1") Long id) ;
}
