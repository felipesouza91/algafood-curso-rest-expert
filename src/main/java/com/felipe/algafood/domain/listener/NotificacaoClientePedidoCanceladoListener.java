package com.felipe.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.felipe.algafood.domain.event.PedidoCanceladoEvent;
import com.felipe.algafood.domain.service.EnvioEmailService;
import com.felipe.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoCancelarPedido(PedidoCanceladoEvent canceladoEvent) {
		var pedido = canceladoEvent.getPedido();
		var mensagem = Mensagem.builder().assunto(pedido.getRestaurante().getId() + " - Pedido Cancelado" )
				.corpo("pedido-cancelado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();	

		envioEmailService.enviar(mensagem);
	}
}
