package fr.bridgefield.brr.services.mail;

import java.util.Locale;

import org.springframework.mail.MailException;

import fr.bridgefield.brr.dao.entity.Organization;
import fr.bridgefield.brr.dao.entity.User;
import fr.bridgefield.brr.security.entity.Principal;
import jakarta.mail.MessagingException;

public interface MailService {

    public void sendSimpleMessage(String to, String subject, String text) throws MailException, MessagingException;

	public void sendValidationMail(User user) throws MailException;
	
	public void sendValidationMail(Organization organization) throws MailException;

	public void sendValidationMail(Principal body, String mail, Locale locale) throws MailException;;

}
