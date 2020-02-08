package com.felipe.algafood.api.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.AlgaLinks;
import com.felipe.algafood.api.controller.UsuarioController;
import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoManager extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public UsuarioDtoManager() {
		super(UsuarioController.class, UsuarioModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));
		usuarioModel.add(algaLinks.linkToGruposUsuarios(usuario.getId(),"grupos-usuarios"));
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(algaLinks.linkToUsuarios());
	}

	public Usuario converterToDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public void copyToDomainObject(UsuarioInput objectInput, Usuario object) {
		modelMapper.map(objectInput, object);
	}

	public Usuario converterToDomainObject(UsuarioInputNoPassword usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputNoPassword objectInput, Usuario object) {
		modelMapper.map(objectInput, object);
	}
}
