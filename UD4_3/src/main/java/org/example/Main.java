package org.example;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Sender's email ID and password need to be specified
        final String from = "your_email@gmail.com";
        final String password = "your_password";

        // Assuming you are sending an email from a Gmail account
        String host = "smtp.gmail.com";

        // Setup mail server properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Get the default Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method
            message.setFrom(new InternetAddress(from));

            // Set the "To" header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient_email@example.com"));

            // Set the "Subject" header field
            message.setSubject("Email with Embedded Image");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // HTML message
            String htmlText = "<H1>Hello</H1><img src=\"cid:image_id\">";
            messageBodyPart.setContent(htmlText, "text/html");

            // Add it
            multipart.addBodyPart(messageBodyPart);

            // Second part is the image
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("path/to/image.jpg");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image_id>");

            // Add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Set the complete message parts
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
