package com.felipe.algafood.core.security.authorizationserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.repository.UsuarioRepository;

@Service
public class JpaUserDatailService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com o email informado"));
		System.out.println(usuario.getNome());
		//TODO: Remover apos criptografar as senhas 
		//usuario.setSenha(encode.encode(usuario.getSenha()));
		return new AuthUser(usuario, getAuthoriteies(usuario));
	}

	private Collection<GrantedAuthority> getAuthoriteies(Usuario usuario) {
		return usuario.getGrupos().stream()
						.flatMap(grupo -> grupo.getPermissoes().stream())
							.map(permissao -> new SimpleGrantedAuthority(permissao.getNome())).collect(Collectors.toSet());
		
	}

}
