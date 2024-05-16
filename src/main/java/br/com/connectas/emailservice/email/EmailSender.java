package br.com.connectas.emailservice.email;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.GetSendQuotaResult;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

public class EmailSender {

    private AmazonSimpleEmailService sesClient;

    public EmailSender() {
    	this.sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public void sendEmail(String from, String to, String subject, String bodyText, String bodyHtml) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                    new Destination().withToAddresses(to))
                .withMessage(new Message()
                    .withBody(new Body()
                        .withHtml(new Content()
                            .withCharset("UTF-8").withData(bodyHtml))
                        .withText(new Content()
                            .withCharset("UTF-8").withData(bodyText)))
                    .withSubject(new Content()
                        .withCharset("UTF-8").withData(subject)))
                .withSource(from);

            
            
            final SendEmailResult result = sesClient.sendEmail(request);
            
            final String messageId = result.getMessageId();
            
            System.out.println("Email sent ".concat(messageId));
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
    
    public  void checkQuota() {
        

        GetSendQuotaResult quotaResult = this.sesClient.getSendQuota();
        System.out.println("Max 24hr Send: " + quotaResult.getMax24HourSend());
        System.out.println("Max Send Rate: " + quotaResult.getMaxSendRate());
        System.out.println("Sent last 24hrs: " + quotaResult.getSentLast24Hours());
    }
    
    public static void main(String[] args) {
		final EmailSender sender = new EmailSender();
//		sender.checkQuota();
		sender.sendEmail("sistema@elofrete.com.br", "fabio.godoy@gmail.com", "Olá palestra", "Este é um teste", "<b>Este é um teste</b>");
	}
}

