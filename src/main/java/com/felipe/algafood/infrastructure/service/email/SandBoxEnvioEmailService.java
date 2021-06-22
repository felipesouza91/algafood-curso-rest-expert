package com.felipe.algafood.infrastructure.service.email;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.felipe.algafood.domain.exception.EmailException;

public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {

	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = super.emailTemplateProcessor.processarTemplate(mensagem);
			var mimeMessage = super.mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setTo(emailProperties.getSandBox().getDestinatario());
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());	
			helper.setText(corpo, true);
			super.mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new  EmailException("Não foi possivel enviar e-mail", e);
		}
	}
	
}
