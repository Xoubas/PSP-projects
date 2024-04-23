package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) {
        String sender = "ivancabaleiroficial@outlook.es";
        String receiver = "a21gonzalocm@iessanclemente.net";
        Properties prop = null;
        try (InputStream input = new FileInputStream("src/main/resources/smtp.properties")) {
            prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            System.out.println(prop.getProperty("mail.smtp.host"));
            System.out.println(prop.getProperty("mail.smtp.port"));
            System.out.println(prop.getProperty("mail.smtp.auth"));
            System.out.println(prop.getProperty("mail.smtp.starttls.enalbe"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, "Buensistema");
            }
        };
        Session session = Session.getInstance(prop, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("Correo importante");
            //Email's body
            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(bodyPart);
//            multipart.addBodyPart(attachmentPart);
            message.setText("La mano arriba cintura sola la media vuelta...");

            Transport.send(message);
            System.out.println("Email sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending mail.");
        }
    }
}
