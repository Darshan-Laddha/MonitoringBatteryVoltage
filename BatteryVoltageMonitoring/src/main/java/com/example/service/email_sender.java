package com.example.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class email_sender {

	public static void sendmail(String destination_email, String car_no, Double voltage) throws Exception {
		System.out.println(destination_email+" is the destination email");
		   System.out.println(car_no+" is the car no");
		   System.out.println(voltage+"is the voltage");
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("darshanladdha1998@gmail.com", "krish&123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("darshanladdha1998@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination_email));
		   msg.setSubject("Tutorials point email");
		   String content="Careful!!!Car"+car_no+" voltage dropped below threshold.The voltage is "+voltage;
		   msg.setContent(content, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		 
		   messageBodyPart.setContent(content, "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   Transport.send(msg);
		// TODO Auto-generated method stub
	}

}
