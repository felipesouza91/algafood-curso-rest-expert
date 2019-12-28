package com.felipe.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="usuario")
@Data
@EqualsAndHashCode( onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@CreationTimestamp
	@Column(nullable =  false, updatable = false)
	private OffsetDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name="usuario_grupo",
				joinColumns = @JoinColumn(name="usuario_id"),
				inverseJoinColumns = @JoinColumn(name="grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean adicionarGrupo(Grupo grupo) {
		return this.getGrupos().add(grupo);
	}

	public boolean removerGrupo(Grupo grupo) {
		return this.getGrupos().remove(grupo);
	}
	
}
