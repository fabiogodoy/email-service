package br.com.connectas.emailservice.adapter.out.repository;

import java.util.Optional;

import br.com.connectas.emailservice.domain.entity.users.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsernameIgnoreCaseAndPassword(String email, String presentedPassword);

	Optional<User> findByUsernameIgnoreCase(String email);

	
}
