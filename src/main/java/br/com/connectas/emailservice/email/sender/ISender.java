package br.com.connectas.emailservice.email.sender;

import org.springframework.stereotype.Component;

@Component
public interface ISender {
	
	
	Boolean send(final String from, final String to, final String subject, final String message);

}
