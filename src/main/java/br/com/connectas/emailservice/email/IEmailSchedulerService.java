package br.com.connectas.emailservice.email;

public interface IEmailSchedulerService {

	Long send(Email email);

	Boolean checkStatus(Long id);

}