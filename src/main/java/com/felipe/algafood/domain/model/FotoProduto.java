package com.felipe.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="foto_produto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

	@Id
	@Column(name="produto_id")
	@EqualsAndHashCode.Include
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	private String descricao;
	
	@Column(name="content_type")
	private String contentType;
	
	private Long tamanho;
	
	public Long getRestauranteId() {
		if (getProduto() != null) {
			return this.getProduto().getRestaurante().getId();
		}
		return null;
	}
}
