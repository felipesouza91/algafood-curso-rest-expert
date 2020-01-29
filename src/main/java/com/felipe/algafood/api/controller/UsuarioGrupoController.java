package com.felipe.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.docs.UsuarioGrupoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.GrupoDtoManager;
import com.felipe.algafood.api.dto.model.GrupoModel;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{id}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GrupoModel> listarTodos(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		return this.dtoManager.toCollectionDtoModel(usuario.getGrupos());
	}
	
	@PutMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.associarGrupo(id, idGrupo);
	}
	
	@DeleteMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.desassociarGrupo(id, idGrupo);
	}
}
