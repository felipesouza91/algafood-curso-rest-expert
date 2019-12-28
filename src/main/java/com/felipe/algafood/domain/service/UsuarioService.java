package com.felipe.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.NegocioException;
import com.felipe.algafood.domain.exception.UsuarioNaoEncontradaException;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.repository.UsuarioRepository;

import lombok.Getter;

@Service
public class UsuarioService {

	@Autowired
	@Getter
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Transactional
	public List<Usuario> buscarTodos() {
		return this.usuarioRepository.findAll();
	}
	
	@Transactional
	public Usuario buscarPorId( Long id ) {
		return this.usuarioRepository.findById(id)
					.orElseThrow(() -> new UsuarioNaoEncontradaException(id));
	}
	
	@Transactional
	public Usuario salvar( Usuario usuario ) {
		this.usuarioRepository.detach(usuario); 
		Optional<Usuario> usuarioExistente = this.usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuario cadastrado com o email: %s", usuario.getEmail()));
		}
		return this.usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.usuarioRepository.deleteById(id);
			this.usuarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Usuário de codigo %d não pode ser removida, pois esta em uso", id));
		}
	}

	@Transactional
	public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
		Usuario usuario = this.buscarPorId(id);
		if( !usuario.getSenha().equals(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		} 
		usuario.setSenha(novaSenha);
		this.salvar(usuario);
	}
	
	@Transactional
	public void associarGrupo(Long idUsuario, Long idGrupo) {
		Usuario usuario = this.buscarPorId(idUsuario);
		Grupo grupo = this.grupoService.buscarPorId(idGrupo);
		usuario.adicionarGrupo(grupo);
	}
	
	@Transactional
	public void desassociarGrupo(Long idUsuario, Long idGrupo) {
		Usuario usuario = this.buscarPorId(idUsuario);
		Grupo grupo = this.grupoService.buscarPorId(idGrupo);
		usuario.removerGrupo(grupo);
	}
}
