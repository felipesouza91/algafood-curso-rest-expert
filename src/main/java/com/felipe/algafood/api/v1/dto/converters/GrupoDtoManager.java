package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.GrupoController;
import com.felipe.algafood.api.v1.dto.inputs.GrupoInput;
import com.felipe.algafood.api.v1.dto.model.GrupoModel;
import com.felipe.algafood.core.security.AlgaSecurity;
import com.felipe.algafood.domain.model.Grupo;

@Component
public class GrupoDtoManager extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity security;
	
	public GrupoDtoManager() {
		super(GrupoController.class, GrupoModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel model = createModelWithId(grupo.getId(), grupo);
		modelMapper.map(grupo, model);
		if ( security.podeConsultarUsuarioGrupoPermissao()) {
			model.add(algaLinks.linkToGrupos("grupos"));
			model.add(algaLinks.linkToGrupoPermissoes(grupo.getId(),"permissoes"));
		}
		
		return model;
	}
	
	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToGrupos());
	}
	
	
	public Grupo converterToDomainObject(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}


	public void copyToDomainObject(GrupoInput objectInput, Grupo object) {
		modelMapper.map(objectInput, object);
	}

}
