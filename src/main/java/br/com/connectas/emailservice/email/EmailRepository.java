package br.com.connectas.emailservice.email;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
	List<Email> findBySentFalse();
}
