package com.felipe.algafood;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.util.BasicMethodsTest;
import com.felipe.algafood.util.DatabaseCleaner;
import com.felipe.algafood.util.FabricaDeEntidades;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteProdutoControllerIT implements BasicMethodsTest {

	@LocalServerPort
	private int port = 8080;
	
	@Autowired
	private FabricaDeEntidades entityFacotry;
	
	Restaurante restaurante;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@SuppressWarnings("unused")
	private String produtoComCampoAMais;

	@SuppressWarnings("unused")
	private int totalDeProdutosPreparadas;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		restaurante = entityFacotry.getRestaurante();
		this.prepararDados();
	}

	@Override
	public void aoBuscardeveRetornarTodasAsEntidadesEStatusOK() {
		// TODO Auto-generated method stub
	}

	@Override
	public void aoBuscardeveRetornarEntidade_QuandoCodigoValido() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoBuscardeveRetorarNotFound_QuandoCodigoInValido() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoSalvardeveRetornarCreated_QuandoEntradasCorretas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoSalvardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoAtualizardeveRetornarNotFound_QuandoCodigoInValido() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoAtualizardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoAtualizardeveRetornarOK_QuandoEntradasCorretas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoExcluirRetornarNoContent_QuandoCodigoCorreto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoExcluirRetornarNotFound_QuandoCodigoIncorreto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aoExcluirRetornarConflict_QuandoEntidadeEmUso() {
		// TODO Auto-generated method stub

	}
	
	private void prepararDados() {
		Produto produto = new Produto();
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		
		produto.setNome("Produto");
		produto.setPreco(BigDecimal.TEN);
		produto.setAtivo(true);
		produto.setRestaurante(restaurante);
		
		produto1.setNome("Produto 1");
		produto1.setPreco(BigDecimal.ONE);
		produto1.setAtivo(true);
		produto1.setRestaurante(restaurante);
		
		produto2.setNome("Produto 3");
		produto2.setPreco(BigDecimal.TEN);
		produto2.setAtivo(true);
		produto2.setRestaurante(restaurante);
		
		this.totalDeProdutosPreparadas = 3;
	}

}
