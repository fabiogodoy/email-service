package br.com.connectas.emailservice.adapter.out.repository;

import java.util.List;

import br.com.connectas.emailservice.domain.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
	List<Email> findBySentFalse();
}
