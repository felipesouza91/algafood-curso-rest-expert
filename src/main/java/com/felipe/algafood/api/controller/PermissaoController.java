package com.felipe.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.docs.PermissaoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.PermissaoDtoManager;
import com.felipe.algafood.api.dto.model.PermissaoModel;
import com.felipe.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE	)
public class PermissaoController implements PermissaoControllerOpenApi {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoDtoManager dtoManager;

	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<PermissaoModel> listarTodas() {
		return dtoManager.toCollectionModel(permissaoRepository.findAll());
	}

}
