package br.com.connectas.emailservice.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.connectas.emailservice.users.User;
import br.com.connectas.emailservice.users.UserRepository;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
    private UserRepository repository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String presentedPassword = authentication.getCredentials().toString();
        final User user = repository.findByUsernameIgnoreCaseAndPassword(username, presentedPassword)
                .orElseThrow(() -> {
					return new BadCredentialsException("The username or password is invalid");
				});

        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
