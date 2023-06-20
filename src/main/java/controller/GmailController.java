package controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailController {
    public void send(String messageText) {

        final String username = "cum69cum69cum69cum69@gmail.com";
        final String password = "urfbhrgdttyefmdu";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("stas.oger.99@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Send by mail");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
