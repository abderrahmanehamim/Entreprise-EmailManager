package MailFunctions;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	

	public static void sendMail(String recipient) throws MessagingException {
		
		System.out.println("Preparing...");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		String  acc = "manallaar3@gmail.com";
		String pwd = "goazulyjjzdzkuta";
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(acc, pwd);
			}
			
		});
		
		Message message = prepareMessage(session, acc, recipient);
		Transport.send(message);
		System.out.println("Mess sent...");
	}
	
	private static Message prepareMessage(Session sec, String acc, String rec) {
		
		Message message = new MimeMessage(sec);
	
			try {
				message.setFrom(new InternetAddress(acc));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
				message.setSubject("gejvdvuz");
				message.setText("wassup");

				return message;
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			
			
	
		return null;
	}
	

}
