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

import com.felipe.algafood.api.dto.inputs.GrupoInput;
import com.felipe.algafood.api.exceptionhandler.ProblemType;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.service.GrupoService;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class GrupoControllerIT {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private GrupoService grupoService;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	private String grupoComCampoAMais;
	
	private int totalGrupoPreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/grupos";
		databaseCleaner.clearTables();
		grupoComCampoAMais = ResourceUtils.getContentFromResource("/json/incorreto/grupo-com-campo-inexistente.json");
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
			.body("", Matchers.hasSize(totalGrupoPreparadas));
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
			.body("nome", equalTo("Administrativo"));
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
		GrupoInput input = new GrupoInput();
		input.setNome("Supervisao");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("nome", equalTo("Supervisao"));
	}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.grupoComCampoAMais)
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
		GrupoInput input = new GrupoInput();
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
		GrupoInput input = new GrupoInput();
		input.setNome("Supervisor");
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
		GrupoInput input = new GrupoInput();
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
			.body(this.grupoComCampoAMais)
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
		GrupoInput input = new GrupoInput();
		input.setNome("Supervisao");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Supervisao"))
			.body("id", equalTo(1));
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
		Grupo grupo = new Grupo();
		Grupo grupo1 = new Grupo();
		Grupo grupo2 = new Grupo();
		
		grupo.setNome("Administrativo");
		grupo1.setNome("Operacional");
		grupo2.setNome("Usuario");
		
		grupoService.salvar(grupo);
		grupoService.salvar(grupo1);
		grupoService.salvar(grupo2);
		
		this.totalGrupoPreparadas= 3;
	}
	
}
