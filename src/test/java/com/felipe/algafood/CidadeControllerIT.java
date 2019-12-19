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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CidadeControllerIT {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService estadoService;
	
	private Estado estadoPadrao;

	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	private int totalCidadePreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cidades";
		databaseCleaner.clearTables();
		preparaEstado();
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
			.body("", Matchers.hasSize(totalCidadePreparadas));
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
		
	}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		
	}
	
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		
	}
	
	@Test
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		
	}
	
	
	private void preparaEstado() {
		Estado estado = new Estado();
		estado.setNome("Rio de janeiro");
		estadoPadrao = estadoService.salvar(estado);
		
	}

	
	private void prepararDados() {
		
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de janeiro");
		cidade.setEstado(estadoPadrao);
		cidadeService.salvar(cidade);
		
		Cidade cidade2 = new Cidade();
		cidade2.setNome("São Paulo");
		cidade.setEstado(estadoPadrao);
		cidadeService.salvar(cidade2);
		
		totalCidadePreparadas = 2;
	}
}
