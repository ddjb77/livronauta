package com.livronauta.login_cadastro.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
	public class EmailService {

	    @Autowired
	    private JavaMailSender mailSender;

	    public void enviarConfirmacaoEmail(String destinatario, String codigo) throws MessagingException {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setFrom("adm.livronauta@gmail.com");
	        helper.setTo(destinatario);
	        helper.setSubject("Confirmação de E-mail - Livronauta");

	        String texto = "<html><body>"
	                + "<p>Olá, agradecemos por escolher o Livronauta como seu gerenciador de leitura! <3</p>"
	                + "<p>Por favor, confirme seu e-mail clicando no link abaixo:</p>"
	                + "<p><a href=\"http://localhost:8080/confirmacao-email?codigo=" + codigo + "\">Confirmar E-mail</a></p>"
	                + "<p>Bem-vindo!</p>"
	                + "</body></html>";

	        helper.setText(texto, true);
	        mailSender.send(message);
	    }
	}

