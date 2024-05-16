package br.com.connectas.emailservice.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsernameIgnoreCaseAndPassword(String email, String presentedPassword);

	Optional<User> findByUsernameIgnoreCase(String email);

	
}
