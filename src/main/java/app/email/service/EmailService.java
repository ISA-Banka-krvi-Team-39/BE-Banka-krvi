package app.email.service;

import app.email.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value
    ("${spring.mail.username}") private String sender;
    
    public void sendWelcomeMail(EmailDetails details)
    {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "utf-8");
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody(), true);
            helper.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
        }
        catch (Exception ignored) {
        }
    }
}
