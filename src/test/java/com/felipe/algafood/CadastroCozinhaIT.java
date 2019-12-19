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

import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.service.CozinhaService;
import com.felipe.algafood.util.BasicMethodsTest;
import com.felipe.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT implements BasicMethodsTest {

	@LocalServerPort
	private int port = 8080;

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	private int totalDeCozinasPreparadas;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
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
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaService.salvar(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaService.salvar(cozinha2);
		
		this.totalDeCozinasPreparadas = (int) this.cozinhaService.getCozinhaRepository().count();
	}


}
