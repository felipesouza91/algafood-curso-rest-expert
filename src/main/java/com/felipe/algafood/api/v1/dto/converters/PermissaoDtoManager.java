package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.PermissaoController;
import com.felipe.algafood.api.v1.dto.model.PermissaoModel;
import com.felipe.algafood.domain.model.Permissao;

@Component
public class PermissaoDtoManager extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PermissaoDtoManager() {
		super(PermissaoController.class, PermissaoModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PermissaoModel toModel(Permissao entity) {
		return modelMapper.map(entity, PermissaoModel.class);
	}

	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToPermissoes());
	}
}
