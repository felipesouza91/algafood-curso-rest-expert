package com.felipe.algafood.api.dto.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.controller.UsuarioController;
import com.felipe.algafood.api.controller.UsuarioGrupoController;
import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoManager extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	
	

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDtoManager() {
		super(UsuarioController.class, UsuarioModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		usuarioModel.add(linkTo(methodOn(UsuarioController.class).listarTodos()).withRel("usuarios"));
		usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class).listarTodos(usuarioModel.getId())).withRel("grupos-usuarios"));
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
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
