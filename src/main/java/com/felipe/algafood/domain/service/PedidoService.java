package com.felipe.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.algafood.domain.exception.EntidadeEmUsoException;
import com.felipe.algafood.domain.exception.NegocioException;
import com.felipe.algafood.domain.exception.PedidoNaoEncontradaException;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.repository.PedidoRepository;

import lombok.Getter;

@Service
public class PedidoService {

	@Autowired
	@Getter
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@Transactional
	public List<Pedido> buscarTodos() {
		return this.pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido buscarPorId(Long id) {
		return this.pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradaException(id));
	}
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		this.validarPedido(pedido);
		this.validarItens(pedido);
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		return this.pedidoRepository.save(pedido);
	}

	@Transactional
	public void excluir(Long id) {
		this.buscarPorId(id);
		try {
			this.pedidoRepository.deleteById(id);
			this.pedidoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Pedido %d não pode ser removida, pois esta em uso", id));
		}
	}
	
	private void validarPedido(Pedido pedido) {
		Cidade cidade = this.cidadeService.buscarPorId(pedido.getEnderecoEntrega().getCidade().getId());
		Restaurante restaurante = this.restauranteService.buscarPorId(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarById(pedido.getFormaPagamento().getId());
		Usuario cliente = this.usuarioService.buscarPorId(1L);
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(cliente);
		
		if(restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
					formaPagamento.getDescricao()));
		}
		
	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = this.produtoService.buscarPorIdRestauranteEProduto(pedido.getRestaurante().getId(), item.getProduto().getId());
			item.setPrecoUnitario(produto.getPreco());
			item.setProduto(produto);
			item.setPedido(pedido);
		});
	}
}
