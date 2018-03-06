package ru.catstack.sc_project.utils;

import ru.catstack.sc_project.resources.MailNeed;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    private static Properties props;
    private static Session session;

    static {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.checkserveridentity", "false");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailNeed.CATSTACK.getUser(), MailNeed.CATSTACK.getPass());
                    }
                });
    }

    public static void sendMessage(String toMail, String name, String textMessage) throws MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MailNeed.CATSTACK.getUser()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        message.setSubject(name);
        message.setText(textMessage);

        Transport.send(message);
    }

}
