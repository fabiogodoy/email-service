package br.com.connectas.emailservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.connectas.emailservice.users.UserRepository;

@Component
public class UserFilter implements Filter {

	@Autowired
	UserRepository repository;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal != null) {
            //here we already authenticate the user
            final String username = (String) principal;
            repository.findByUsernameIgnoreCase(username).ifPresent(user -> UserResolver.setUserId(user.getId()));
        }
		
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
