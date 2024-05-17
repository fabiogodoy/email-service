package br.com.connectas.emailservice.domain.usecase.sender;

import java.util.Objects;

import br.com.connectas.emailservice.adapter.out.interfaces.email.sender.ISender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

@Component
@Primary
public class AmazonEmailSender implements ISender {

	@Override
	public Boolean send(final String from, final String to, final String subject, final String message) {
		Boolean sent = false;
		try {
			final AmazonSimpleEmailService sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
	                .withRegion(Regions.US_WEST_2)
	                .build();
            SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                    new Destination().withToAddresses(to))
                .withMessage(new Message()
                    .withBody(new Body()
                        .withHtml(new Content()
                            .withCharset("UTF-8").withData(message))
                        .withText(new Content()
                            .withCharset("UTF-8").withData(message)))
                    .withSubject(new Content()
                        .withCharset("UTF-8").withData(subject)))
                .withSource(from);

            
            
            final SendEmailResult result = sesClient.sendEmail(request);
            
            final String messageId = result.getMessageId();
            
            System.out.println("Email sent ".concat(messageId));
            
            sent = Objects.nonNull(messageId);
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
		
		return sent;
	}

}
