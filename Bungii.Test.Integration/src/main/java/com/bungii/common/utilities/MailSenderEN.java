package com.bungii.common.utilities;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSenderEN {

    /**
     * Send mail via SSL/TLS enabled SMTP server.
     * @param addresser Could be different with sender email.
     * @param subject   Subject of mail.
     * @param to        Addressee.
     * @param cc        Carbon copy.
     * @param bcc       Blind carbon copy.
     * @param content   Content of mail.
     * @Param mimeType  MIME type of {@param content}， e.g. -> 'text/plain', 'text/html;charset=utf-8'
     * @return
     */
    public static void send(String addresser, String subject, String to, String cc, String bcc, String content, String mimeType) {
        // Init constants of sender email account.
        String email = "vishal.bagi.cci@gmail.com";;
        String password = "password";
        String host = "smtp.googlemail.com"; // e.g. -> "smtp.google.com"
        String port = "465"; // e.g. -> "465" "587"

        // Set up properties.
        Properties props = System.getProperties();
        props.put("mail.smtp.user", email);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.trust", host); // Change host to "*" if you want to trust all host.

        // Set up message.
        MimeMessage message = new MimeMessage(Session.getDefaultInstance(props));
        try {
            message.setFrom(new InternetAddress(addresser));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(bcc));
            message.setSubject(subject);
            message.setContent(content, mimeType);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            // Send mail.
            Transport.send(message, email, password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}