package fr.bridgefield.brr.services.mail;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import fr.bridgefield.brr.dao.entity.Organization;
import fr.bridgefield.brr.dao.entity.User;
import fr.bridgefield.brr.security.entity.Principal;
import fr.bridgefield.brr.services.localization.LocalizationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class MailServiceImpl implements MailService  {
    
	private final static String FR_BRIDGEFIELD_VALIDATION_MESSAGE_KEY="fr.bridgefield.validationMessage";
	
	@Value("${fr.bridgefield.mail.sender.email}")
	String senderEmail;
	
	@Value("${fr.bridgefield.security.validation.urlPattern}")
	String validationUrl;
	
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	@Qualifier("messageService")
	private LocalizationService localizationService;

    public void sendSimpleMessage (
      String to, String subject, String text) throws MailException, MessagingException{
        
    	MimeMessage mimeMessage = emailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
 
    	helper.setTo(to);
        helper.setFrom(senderEmail);
        helper.setReplyTo(senderEmail);
        helper.setSubject(subject); 
        helper.setText(text);
        emailSender.send(mimeMessage);
        
    }

	@Override
	public void sendValidationMail(User user) throws MailException {
		String url = validationUrl.formatted(user.getId());
		String text = localizationService.getMessage(FR_BRIDGEFIELD_VALIDATION_MESSAGE_KEY, user.getPreferredLocale(), url);
		try {
			sendSimpleMessage(user.geteMail(), "Validation", text);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendValidationMail(Organization organization) throws MailException {
		String url = validationUrl.formatted(organization.getId());
		String text = localizationService.getMessage(FR_BRIDGEFIELD_VALIDATION_MESSAGE_KEY, organization.getPreferredLocale(), url);
		try {
			sendSimpleMessage(organization.getContactMail(), "Validation", text);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendValidationMail(Principal principal, String mail, Locale locale) throws MailException {

		String url = validationUrl.formatted(principal.getId());
		String text = localizationService.getMessage(FR_BRIDGEFIELD_VALIDATION_MESSAGE_KEY, locale, url);		
		try {
			sendSimpleMessage(mail, "Validation", text);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}