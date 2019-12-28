package com.felipe.algafood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);

}
