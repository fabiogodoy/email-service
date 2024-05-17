package br.com.connectas.emailservice.domain.usecase;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import br.com.connectas.emailservice.domain.entity.Email;
import br.com.connectas.emailservice.adapter.out.repository.EmailRepository;
import br.com.connectas.emailservice.adapter.out.interfaces.email.IEmailSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.connectas.emailservice.adapter.out.interfaces.email.sender.ISender;
import br.com.connectas.emailservice.config.filter.UserResolver;
import br.com.connectas.emailservice.domain.entity.users.User;
import br.com.connectas.emailservice.adapter.out.repository.UserRepository;

@Component
@Primary
public class EmailSchedulerService implements IEmailSchedulerService {

	@Autowired
	private EmailRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ISender sender;
	
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	@Override
	public Long send(Email email) {
		Long id = null;

		try {
			
			if (Objects.nonNull(email.getRecipient()) && pattern.matcher(email.getRecipient()).matches()) {
				email.setOwnerId(UserResolver.getUserId());
				email = this.repository.save(email);
				id = email.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public Boolean checkStatus(Long id) {
		Boolean sent = false;
		final Optional<Email> opt = this.repository.findById(id);

		if (opt.isPresent()) {
			sent = opt.get().isSent();
		}

		return sent;
	}

	private void realSend(Email email) {
		final Optional<User> opt = this.userRepository.findById(email.getOwnerId());

		opt.ifPresent(user -> {
			final Boolean sent = sender.send(user.getFromEmail(), email.getRecipient(), email.getSubject(),
					email.getBody());

			if (Boolean.TRUE.equals(sent)) {
				email.setSent(true);
				repository.save(email);
			}
		});

	}

	@Scheduled(fixedRate = 60000)
	public void sendUnsentEmails() {
		System.out.println("Email Service Running");
		final List<Email> unsentEmails = repository.findBySentFalse();
		for (Email email : unsentEmails) {
			realSend(email);
		}
	}

}
