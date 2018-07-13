package java_study;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * We did the following:
 * Downloaded and installed latest mail.jar and activation.jar
 * to local root directory: C:\jars4study
 * Then various attempts revealed we can not send emails without having local
 * smtp server. Therefore I downloaded and installed locally:
 * smtp4dev which required also .NET framework which installed too.
 * Now we can not Windows send emails locally.
 * Defender Firewall is not a problem.
 */



public class SendEmail {

	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		String to = "kianicka@email.cz";

		// Sender's email ID needs to be mentioned
		String from = "kianicka@microstep-mis.sk";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();
		System.out.println("properties.keys:" + properties.keys());

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		//properties.setProperty("mail.smtp.port", "8080");
		//properties.put("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
