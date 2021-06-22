package com.felipe.algafood.infrastructure.service.email;

import com.felipe.algafood.domain.service.EnvioEmailService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {

	@Autowired
	private EmailTemplateProcessor emailTemplateProcessor;

	@Override
	public void enviar(Mensagem mensagem) {
		String corpo = emailTemplateProcessor.processarTemplate(mensagem);

		log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}
}
