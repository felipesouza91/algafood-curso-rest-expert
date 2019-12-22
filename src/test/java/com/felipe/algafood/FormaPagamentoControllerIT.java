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

import com.felipe.algafood.api.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.exceptionhandler.ProblemType;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.service.FormaPagamentoService;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class FormaPagamentoControllerIT {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	private String formaPagamentoComCampoAmais;
	
	private int totalFormaPagamentoPreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/formaspagamentos";
		databaseCleaner.clearTables();
		formaPagamentoComCampoAmais = ResourceUtils.getContentFromResource("/json/incorreto/forma-pagamento-com-campo-inexistente.json");
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
			.body("", Matchers.hasSize(totalFormaPagamentoPreparadas));
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
			.body("descricao", equalTo("A vista"));
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
		FormaPagamentoInput input = new FormaPagamentoInput();
		input.setDescricao("Boleto");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("descricao", equalTo("Boleto"));
	}
	
	@Test
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.formaPagamentoComCampoAmais)
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
		FormaPagamentoInput input = new FormaPagamentoInput();
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
		FormaPagamentoInput input = new FormaPagamentoInput();
		input.setDescricao("A Vista");
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
		FormaPagamentoInput input = new FormaPagamentoInput();
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
			.body(this.formaPagamentoComCampoAmais)
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
		FormaPagamentoInput input = new FormaPagamentoInput();
		input.setDescricao("A vista 2");
		RestAssured.given()
			.body(input)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("descricao", equalTo("A vista 2"))
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
		FormaPagamento formaPagamento = new FormaPagamento();
		FormaPagamento formaPagamento2 = new FormaPagamento();
		FormaPagamento formaPagamento3 = new FormaPagamento();
		
		formaPagamento.setDescricao("A vista");
		formaPagamento2.setDescricao("Credito");
		formaPagamento3.setDescricao("Debito");
		
		this.formaPagamentoService.salvar(formaPagamento);
		this.formaPagamentoService.salvar(formaPagamento2);
		this.formaPagamentoService.salvar(formaPagamento3);
		
		this.totalFormaPagamentoPreparadas = 3;
	}
	
}
