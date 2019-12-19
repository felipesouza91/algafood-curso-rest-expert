package com.felipe.algafood.util;

public interface BasicMethodsTest {
	
	public void aoBuscardeveRetornarTodasAsEntidadesEStatusOK();
	
	public void aoBuscardeveRetornarEntidade_QuandoCodigoValido() ;
	
	public void aoBuscardeveRetorarNotFound_QuandoCodigoInValido() ;
	
	public void aoSalvardeveRetornarCreated_QuandoEntradasCorretas() ;
	
	public void aoSalvardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() ;
	
	public void aoSalvardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() ;
	
	public void aoAtualizardeveRetornarNotFound_QuandoCodigoInValido() ;
	
	public void aoAtualizardeveRetornarBadRequest_QuandoNaoInserirCampoObrigatorio() ;
	
	public void aoAtualizardeveRetornarBadRequest_QuandoIncluirCamposInxistentes() ;
	
	public void aoAtualizardeveRetornarOK_QuandoEntradasCorretas() ;
	
	public void aoExcluirRetornarNoContent_QuandoCodigoCorreto() ;
	
	public void aoExcluirRetornarNotFound_QuandoCodigoIncorreto() ;
	
	public void aoExcluirRetornarConflict_QuandoEntidadeEmUso() ;
}
