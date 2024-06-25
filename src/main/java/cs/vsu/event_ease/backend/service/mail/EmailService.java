package cs.vsu.event_ease.backend.service.mail;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import cs.vsu.event_ease.backend.domain.Invitation;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String emailUsername;

    public void sendSimpleEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailUsername);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
        int i = 1;
    }

    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("Purchase Order", file);
        emailSender.send(mimeMessage);
    }

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendMail(EmailContext email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }

    @SneakyThrows
    public void sendInvitation(Invitation invitation, CloseEvent closeEvent, String qrPath) {
        Map<String, Object> contextMap = new HashMap<>();

        EmailContext email = EmailContext.builder()
                .from(emailUsername)
                .to(invitation.getEmail())
                .subject(String.format("EventEase. Приглашение на событие <%s>!", closeEvent.getName()))
                .templateLocation(String.format("src/main/resources/template_%s.html", invitation.getDesign()))
                .context(contextMap)
                .build();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getContext());
        //String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(
                String.format("Здравствуйте! Вы приглашены на событие %s," +
                        "которое состоится: %s по местоположению: %s." +
                        "Верифицирующий QR-код прикреплён во вложениях к этому письму!",
                        closeEvent.getName(), closeEvent.getDate(), closeEvent.getLocation()), true
        );
        mimeMessageHelper.addAttachment(
                "invitation_qr.png",
                new File(qrPath)
        );
        emailSender.send(message);
    }

}
