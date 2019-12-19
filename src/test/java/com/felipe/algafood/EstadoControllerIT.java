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

import com.felipe.algafood.api.exceptionhandler.ProblemType;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.service.CidadeService;
import com.felipe.algafood.domain.service.EstadoService;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class EstadoControllerIT {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeServie;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;

	
	private String estadoComCampoAMais;
	
	private int totalEstadosPreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/estados";
		databaseCleaner.clearTables();
		estadoComCampoAMais = ResourceUtils.getContentFromResource("/json/incorreto/estado-com-campo-inexistente.json");
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
			.body("", Matchers.hasSize(totalEstadosPreparadas));
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
			.body("nome", equalTo("Rio de janeiro"));
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
		Estado estado = new Estado();
		estado.setNome("Sergipe");
		RestAssured.given()
			.body(estado)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("nome", equalTo("Sergipe"));
	}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.estadoComCampoAMais)
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
		Estado estado = new Estado();
		RestAssured.given()
			.body(estado)
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
		Estado estado = new Estado();
		estado.setNome("5");
		RestAssured.given()
			.body(estado)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 5)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
	}
	
	@Test
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		Estado estado = new Estado();
		RestAssured.given()
			.body(estado)
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
			.body(this.estadoComCampoAMais)
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
		Estado estado = new Estado();
		estado.setNome("Rio de janeiro 2");
		RestAssured.given()
			.body(estado)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Rio de janeiro 2"))
			.body("id", equalTo(1));
	}
	
	@Test
	public void aoExcluirRetornarNoContent_QuandoCodigoCorreto() { 
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 2)
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
		Estado estado = new Estado();
		Estado estado1 = new Estado();
		Estado estado2 = new Estado();
		estado.setNome("Rio de janeiro");
		estado1.setNome("São Paulo");
		estado2.setNome("Minas Gerais");
		estadoService.salvar(estado);
		estadoService.salvar(estado1);
		estadoService.salvar(estado2);
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de janeiro");
		cidade.setEstado(estado);
		cidadeServie.salvar(cidade);
		totalEstadosPreparadas = 3;
	}
	
}
