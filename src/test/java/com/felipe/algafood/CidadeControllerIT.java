package com.felipe.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

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

import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.inputs.EstadoIdInput;
import com.felipe.algafood.api.exceptionhandler.ProblemType;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.Endereco;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.CidadeService;
import com.felipe.algafood.domain.service.CozinhaService;
import com.felipe.algafood.domain.service.EstadoService;
import com.felipe.algafood.domain.service.RestauranteService;
import com.felipe.algafood.util.BasicMethodsTest;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CidadeControllerIT implements BasicMethodsTest  {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private RestauranteService restauranteService;
	
	private Estado estadoPadrao;

	private String cidadeComCampoAMais; 
	
	
	private Cidade cidadeBase ;
	
	private int totalCidadePreparadas;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cidades";
		this.cidadeComCampoAMais = ResourceUtils.getContentFromResource("/json/incorreto/cidade-com-campo-inexistente.json");
		databaseCleaner.clearTables();
		preparaEstado();
		prepararDados();
		
		
	}

	@Test
	@Override
	public void aoBuscardeveRetornarTodasAsEntidadesEStatusOK() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(totalCidadePreparadas));	
	}
	
	@Test
	@Override
	public void aoBuscardeveRetorarNotFound_QuandoCodigoInValido() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", 10)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
		
	}

	@Test
	@Override
	public void aoBuscardeveRetornarEntidade_QuandoCodigoValido() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Rio de janeiro"));
	}

	@Test
	@Override
	public void aoSalvardeveRetornarCreated_QuandoEntradasCorretas() {
		CidadeInput cidade = this.preparaInputCidade("Minas Gerais");
		RestAssured.given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(cidade)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("nome", equalTo("Minas Gerais"));
		
	}

	@Test
	@Override
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		RestAssured.given()
			.body(this.cidadeComCampoAMais)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle()));
	}

	@Test
	@Override
	public void aoSalvardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		// TODO Auto-generated method stub
		CidadeInput cidade = new CidadeInput();
		RestAssured.given()
			.body(cidade)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.DADOS_INVALIDOS.getTitle()));
	}

	@Test
	@Override
	public void aoAtualizardeveRetornarNotFound_QuandoCodigoInValido() {
		// TODO Auto-generated method stub
		CidadeInput cidade = this.preparaInputCidade("Rio de janeiro");
		RestAssured.given()
			.body(cidade)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 10)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));
	}

	@Test
	@Override
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		// TODO Auto-generated method stub
		CidadeInput cidade = new CidadeInput();
		RestAssured.given()
			.body(cidade)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.DADOS_INVALIDOS.getTitle()));
	}

	@Test
	@Override
	public void aoAtualizardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		// TODO Auto-generated method stub
		RestAssured.given()
			.body(this.cidadeComCampoAMais)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle()));
	}

	@Test
	@Override
	public void aoAtualizardeveRetornarOK_QuandoEntradasCorretas() {
		// TODO Auto-generated method stub
		CidadeInput cidade = preparaInputCidade("Rio de janerio 2");
		RestAssured.given()
			.body(cidade)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Rio de janerio 2"));
	}

	@Test
	@Override
	public void aoExcluirRetornarNoContent_QuandoCodigoCorreto() {
		// TODO Auto-generated method stub
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 1)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());

	}

	@Test
	@Override
	public void aoExcluirRetornarNotFound_QuandoCodigoIncorreto() {
		// TODO Auto-generated method stub
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 33)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("title", equalTo(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle()));

	}

	@Test
	@Override
	public void aoExcluirRetornarConflict_QuandoEntidadeEmUso() {
		preparaDadoParaConflito();
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", 1)
		.then()
			.statusCode(HttpStatus.CONFLICT.value())
			.body("title", equalTo(ProblemType.ENTIDADE_EM_USO.getTitle()));
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
		cidadeBase = cidade;
		Cidade cidade2 = new Cidade();
		cidade2.setNome("São Paulo");
		cidade2.setEstado(estadoPadrao);
		cidadeService.salvar(cidade2);
		
		totalCidadePreparadas = 2;
	}

	private CidadeInput preparaInputCidade(String nome) {
		CidadeInput cidade = new CidadeInput();
		cidade.setNome(nome);
		EstadoIdInput idEstado = new EstadoIdInput();
		idEstado.setId(this.estadoPadrao.getId());
		cidade.setEstado(idEstado);
		return cidade;
	}
	
	private void preparaDadoParaConflito() {
		// TODO Auto-generated method stub
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Cozinha");
		cozinhaService.salvar(cozinha);
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Teste Frete Gratis");
		restaurante.setCozinha(cozinha);
		restaurante.setTaxaFrete(BigDecimal.ZERO);
		Endereco endereco = new Endereco();
		endereco.setCidade(cidadeBase);
		restaurante.setEndereco(endereco);
		restauranteService.salvar(restaurante);
	}	

}
