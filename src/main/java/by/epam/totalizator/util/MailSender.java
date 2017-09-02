
package by.epam.totalizator.util;

import by.epam.totalizator.type.MailType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static by.epam.totalizator.constant.GeneralConstant.Mail.*;

public class MailSender extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();

    private String subject;
    private String text;
    private String toEmail;

    public MailSender(String subject, String text, String toEmail) {
        this.subject = subject;
        this.text = text;
        this.toEmail = toEmail;
    }


    @Override
    public void run() {

        Properties props = new Properties();
        props.put(MAIL_SMTP_AUTH, MailType.SMTP_AUTH.getValue());
        props.put(MAIL_SMTP_START_TLS_ENABLE, MailType.SMTP_START_TLS_ENABLE.getValue());
        props.put(MAIL_SMTP_HOST, MailType.SMTP_HOST.getValue());
        props.put(MAIL_SMTP_PORT, MailType.SMTP_PORT.getValue());

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailType.EMAIL.getValue(), MailType.PASSWORD.getValue());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailType.EMAIL.getValue()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            LOGGER.log(Level.INFO, "Mail successfully sent. ");

        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Mail sending error. ", e);
            throw new RuntimeException(e);
        }
    }
}
