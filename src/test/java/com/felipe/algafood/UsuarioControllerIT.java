package com.felipe.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.inputs.UsuarioSenhaInput;
import com.felipe.algafood.api.exceptionhandler.ProblemType;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class UsuarioControllerIT {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	private String usuarioComCampoAMais;
	
	private String usuarioSenhaComCampoAMais;
	
	private int totalUsuarioPreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/usuarios";
		databaseCleaner.clearTables();
		usuarioComCampoAMais = ResourceUtils.getContentFromResource("/json/incorreto/usuario-com-campo-inexistente.json");
		usuarioSenhaComCampoAMais = ResourceUtils.getContentFromResource("/json/incorreto/usuario-senha-com-campo-inexistente.json");
		prepararDados();
	}
	
	
	@Test
	public void aoBuscardeveRetornarTodasAsCidadesEStatusOK() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(totalUsuarioPreparadas));
	}
	
	@Test
	public void aoBuscardeveRetornarCidade_QuandoCodigoValido() {
		
		RestAssured.given()
			.pathParam("id", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Felipe"));
	}
	
	@Test
	public void aoBuscardeveRetorarNotFound_QuandoCodigoInValido() {
		RestAssured.given()
			.pathParam("id", 100)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
	}
	
	@Test
	public void aoSalvardeveRetornarCreated_QuandoEntradasCorretas() {
		UsuarioInput input = new UsuarioInput();
		input.setNome("Dolar");
		input.setEmail("felipe@felipe.com.br");
		input.setSenha("123");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("nome", equalTo("Dolar"));
	}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.usuarioComCampoAMais)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle()));
		}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		UsuarioInput input = new UsuarioInput();
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.DADOS_INVALIDOS.getTitle()));
	}
	
	@Test
	public void aoAtualizardeveRetornarNotFound_QuandoCodigoInValido() {
		UsuarioInputNoPassword input = new UsuarioInputNoPassword();
		input.setNome("Dolar");
		input.setEmail("felipe@felipe.com.br");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 10)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
	}
	
	@Test
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		UsuarioInputNoPassword input = new UsuarioInputNoPassword();
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.DADOS_INVALIDOS.getTitle()));
	}
	
	@Test
	public void aoAtualizardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.usuarioComCampoAMais)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle()));
	}
	
	@Test
	public void aoAtualizardeveRetornarOK_QuandoEntradasCorretas() {
		UsuarioInputNoPassword input = new UsuarioInputNoPassword();
		input.setEmail("teste123@teste.com.br");
		input.setNome("Testet up");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(input.getNome()))
			.body("email", equalTo(input.getEmail()))
			.body("id", equalTo(1));
	}
	
	@Test
	public void aoAtualizarSenhaDeveRetornarNoContent_QuandoEntradasCorretas() {
		UsuarioSenhaInput input = new UsuarioSenhaInput();
		input.setSenhaAtual("123");
		input.setNovaSenha("321");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}/senha", 1)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void aoAtualizarSenhaDeveRetornarNotFound_QuandoCodigoInValido() {
		UsuarioSenhaInput input = new UsuarioSenhaInput();
		input.setSenhaAtual("123");
		input.setNovaSenha("321");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}/senha", 1555)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
	}
	
	@Test
	public void aoAtualizarSenhaDeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		UsuarioSenhaInput input = new UsuarioSenhaInput();
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}/senha", 1555)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.DADOS_INVALIDOS.getTitle()));
	}
	
	@Test
	public void aoAtualizarSenhaDeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.usuarioSenhaComCampoAMais)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}/senha", 1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle()));
	}
	
	@Test
	public void aoExcluirRetornarNoContent_QuandoCodigoCorreto() { 
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 1)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void aoExcluirRetornarNotFound_QuandoCodigoIncorreto() { 
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 150)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void aoExcluirRetornarConflict_QuandoEntidadeEmUso() { 
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 1)
		.then()
			.statusCode(HttpStatus.CONFLICT.value());
	}
	
	private void prepararDados() {
		Usuario usuario = new Usuario();
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario.setNome("Felipe");
		usuario1.setNome("Daniel");
		usuario2.setNome("Danilo");
		
		usuario.setSenha("123");
		usuario1.setSenha("123");
		usuario2.setSenha("123");
		
		usuario.setEmail("teste@gmail.com");
		usuario1.setEmail("123@Email.com");
		usuario2.setEmail("123@123.com.br");
		
		usuarioService.salvar(usuario);
		usuarioService.salvar(usuario1);
		usuarioService.salvar(usuario2);
		
		this.totalUsuarioPreparadas = 3;
	}
	
}
