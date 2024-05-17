package br.com.connectas.emailservice.adapter.out.interfaces.email;

import br.com.connectas.emailservice.domain.entity.Email;

public interface IEmailSchedulerService {

	Long send(Email email);

	Boolean checkStatus(Long id);

}