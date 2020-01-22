package com.felipe.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.felipe.algafood.domain.event.PedidoConfirmadoEvent;
import com.felipe.algafood.domain.service.EnvioEmailService;
import com.felipe.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoConfirmadoEvent confirmadoEvent) {
		var pedido = confirmadoEvent.getPedido();
		var mensagem = Mensagem.builder().assunto(pedido.getRestaurante().getId() + " - Pedido Confirmado" )
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();

		envioEmailService.enviar(mensagem);
	}
}
