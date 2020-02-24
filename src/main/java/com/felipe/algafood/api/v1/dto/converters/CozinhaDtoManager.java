package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.CozinhaController;
import com.felipe.algafood.api.v1.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.v1.dto.model.CozinhaModel;
import com.felipe.algafood.core.security.AlgaSecurity;
import com.felipe.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoManager extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public CozinhaDtoManager() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	@Override
	public CozinhaModel toModel(Cozinha object) {
		CozinhaModel cozinhaModel = createModelWithId(object.getId(), object);
		modelMapper.map(object, cozinhaModel);
		if(algaSecurity.podeConsultarCozinha())
		cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
		return cozinhaModel;
	}

	public Cozinha converterToDomainObject(CozinhaInput objectInput) {
		return modelMapper.map(objectInput, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInput objectInput, Cozinha object) {
		modelMapper.map(objectInput, object);
	}
}
