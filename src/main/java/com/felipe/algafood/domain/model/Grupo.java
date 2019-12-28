package com.felipe.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="grupo")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao",
				joinColumns = @JoinColumn(name="grupo_id"),
				inverseJoinColumns = @JoinColumn(name="permissao_id"))
	private Set<Permissao> permissoes = new HashSet<>();
	
	
	public boolean adicionarPermissao(Permissao permissao) {
		return this.getPermissoes().add(permissao);
	}
	
	public boolean removerPermissao(Permissao permissao) {
		return this.getPermissoes().remove(permissao);
	}
}
