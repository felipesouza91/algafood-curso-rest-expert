package com.felipe.algafood.api.dto.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.controller.CozinhaController;
import com.felipe.algafood.api.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.dto.model.CozinhaModel;
import com.felipe.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoManager extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaDtoManager() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	@Override
	public CozinhaModel toModel(Cozinha object) {
		CozinhaModel cozinhaModel = createModelWithId(object.getId(), object);
		modelMapper.map(object, cozinhaModel);
		cozinhaModel.add(linkTo(CozinhaController.class).withSelfRel());
		return cozinhaModel;
	}

	public Cozinha converterToDomainObject(CozinhaInput objectInput) {
		return modelMapper.map(objectInput, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInput objectInput, Cozinha object) {
		modelMapper.map(objectInput, object);
	}
}
