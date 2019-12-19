package com.felipe.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.felipe.algafood.core.Groups.ProdutoId;
import com.felipe.algafood.core.Groups.RestauranteId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="produto")
@Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	@Id
	@NotNull(groups = ProdutoId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	private Boolean ativo;
	
	@Valid
	@ConvertGroup(from = Default.class, to = RestauranteId.class)
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
}
