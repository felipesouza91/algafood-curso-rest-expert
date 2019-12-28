package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class UsuarioDtoManager implements ApplicationDtoManagerInterface<Usuario, UsuarioModel, UsuarioInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UsuarioModel conveterToDtoModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}
	
	@Override
	public List<UsuarioModel> toCollectionDtoModel( Collection<Usuario> listUsuario) {
		return listUsuario
				.stream()
				.map(usuario -> conveterToDtoModel(usuario)).collect(Collectors.toList());
	}
	
	@Override
	public Usuario converterToDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	@Override
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
