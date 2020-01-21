package com.felipe.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.felipe.algafood.core.email.EmailProperties;
import com.felipe.algafood.domain.exception.EmailException;
import com.felipe.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	protected JavaMailSender mailSender;
	
	@Autowired
	protected EmailProperties emailProperties;
	
	@Autowired
	protected Configuration freemarkConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = this.processarTemplate(mensagem);
			var mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());	
			helper.setText(corpo, true);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new  EmailException("Não foi possivel enviar e-mail", e);
		}
	}

	protected String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkConfig.getTemplate(mensagem.getCorpo());
			 
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new  EmailException("Não foi possivel montar o template do e-mail", e);
		} 
	}
}
