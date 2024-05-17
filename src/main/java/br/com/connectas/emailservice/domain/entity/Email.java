package br.com.connectas.emailservice.domain.entity;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "emails")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String recipient;
	@NonNull
	private String subject;
	private String body;
	private boolean sent = false;
	private Long ownerId;

	public Email() {
	}

	public Email(String recipient, String subject, String body, String from) {
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	@Column(columnDefinition = "TEXT")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

}
