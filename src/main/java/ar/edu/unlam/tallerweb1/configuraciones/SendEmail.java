package ar.edu.unlam.tallerweb1.configuraciones;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class SendEmail {

    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("tallerUnoPruebas@gmail.com");
        mailSender.setPassword("Taller2021");


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
    //A simple email, only text information
    /**
     * @Description:
     * @Param: [subject:title, text:content, email:recipient's email address]
     * @return: void
     * @Author: yourself
     * @Date: 2020/2/27
     */
    public void SendSimpleEmail(String subject, String text, String email) {
        JavaMailSenderImpl javaMailSender = JavaMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject); //The subject of the mail
        mailMessage.setText(text);
        mailMessage.setTo(email); //Who to send to
        mailMessage.setFrom("javier.terranova@gmail.com"); //Who sent it
        javaMailSender.send(mailMessage);
    }

    /**
     * @Description:
     * @Param: [subject: title, text: content, html, Boolean html: whether to parse html
     * email: recipient's email address, attachmentMap: attachment name and file path]
     * @return: void
     * @Author: yourself
     * @Date: 2020/2/27
     */
    public void SendMimeEmail(String subject, String text, Boolean html,
                              String email, Map<String, String> attachmentMap) throws MessagingException {
        JavaMailSenderImpl javaMailSender = JavaMailSender();
        //A complicated mail`
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //Assemble
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, html);
        helper.setSubject(subject);
        helper.setText(text, html);

        //Attachment
        if (attachmentMap != null) {
            Iterator<Map.Entry<String, String>> iterator = attachmentMap.entrySet().iterator();
            //map.entrySet() gets a set collection, which can be traversed using an iterator
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                helper.addAttachment(entry.getKey(),
                        //Attachment name, write the suffix of the file, don't write less and write wrong
                        new File(entry.getValue()));
                //The file address of the attachment can be written as an absolute path. If it is a relative path, such as ./1.png, it represents 1.png under resources
            }
        }
        helper.setTo(email); //Who to send to
        helper.setFrom(Objects.requireNonNull(javaMailSender.getUsername())); //Who sent it
        javaMailSender.send(mimeMessage);
    }
}
