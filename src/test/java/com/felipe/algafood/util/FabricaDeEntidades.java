package com.felipe.algafood.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.CidadeService;
import com.felipe.algafood.domain.service.CozinhaService;
import com.felipe.algafood.domain.service.EstadoService;
import com.felipe.algafood.domain.service.RestauranteService;

@Component
public class FabricaDeEntidades {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private RestauranteService restauranteService;

	
	public Cozinha getCozinha() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Cozinha 1");
		return this.cozinhaService.salvar(cozinha);
	}
	
	public Estado getEstado() {
		Estado estado = new Estado();
		estado.setNome("Rio de janeiro");
		return this.estadoService.salvar(estado);
	}
	
	public Cidade getCidade() {
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de janeiro");
		cidade.setEstado(this.getEstado());
		return this.cidadeService.salvar(cidade);
	}
	
	public Restaurante getRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Restaurante 1");
		restaurante.setTaxaFrete(BigDecimal.ONE);
		restaurante.setCozinha(this.getCozinha());
		return this.restauranteService.salvar(restaurante);
	}
}
