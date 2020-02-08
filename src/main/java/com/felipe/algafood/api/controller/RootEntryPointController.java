package com.felipe.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.AlgaLinks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Root")
public class RootEntryPointController {

	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ApiOperation("Lista todos os links da API")
	public RootEntryPointModel root() {
		RootEntryPointModel entryPointModel = new RootEntryPointModel();
		entryPointModel.add(algaLinks.linkToCozinhas("cozinhas"));
		entryPointModel.add(algaLinks.linkToPedidos("pedidos"));
		entryPointModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		entryPointModel.add(algaLinks.linkToGrupos("grupos"));
		entryPointModel.add(algaLinks.linkToUsuarios("usuarios"));
		entryPointModel.add(algaLinks.linkToPermissoes("permissoes"));
		entryPointModel.add(algaLinks.linkToFormasPagamentos("formas-pagamentos"));
		entryPointModel.add(algaLinks.linkToEstados("estados"));
		entryPointModel.add(algaLinks.LinkToCidades("cidades"));
		entryPointModel.add(algaLinks.LinkToEstatisticas("estatisticas"));
		return entryPointModel;
	}
	

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
		
	}
	
}
