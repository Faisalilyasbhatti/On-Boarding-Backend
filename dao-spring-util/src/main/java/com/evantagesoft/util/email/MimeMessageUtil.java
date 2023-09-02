package com.evantagesoft.util.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MimeMessageUtil {

    private MimeMessageHelper helper;
    private JavaMailSender sender;
    private MimeMessage message;

    public MimeMessageUtil (JavaMailSender sender) {
        this.sender = sender;
        this.message = sender.createMimeMessage();
        this.helper = new MimeMessageHelper(this.message, "utf-8");
    }

    public MimeMessageUtil setTo(String to) throws MessagingException {
        this.helper.setTo(to);
        return this;
    }

    public MimeMessageUtil setTo(String[] tos) throws MessagingException {
        this.helper.setTo(tos);
        return this;
    }

    public MimeMessageUtil setFrom(String from) throws MessagingException {
        this.helper.setFrom(from);
        return this;
    }

    public MimeMessageUtil setSubject(String subject) throws MessagingException {
        this.helper.setSubject(subject);
        return this;
    }

    public MimeMessageUtil setCc(String cc) throws MessagingException {
        this.helper.setCc(cc);
        return this;
    }

    public MimeMessageUtil setCc(String[] ccs) throws MessagingException {
        this.helper.setCc(ccs);
        return this;
    }

    public MimeMessageUtil setBcc(String bcc) throws MessagingException {
        this.helper.setBcc(bcc);
        return this;
    }

    public MimeMessageUtil setBcc(String[] bccs) throws MessagingException {
        this.helper.setBcc(bccs);
        return this;
    }

    public MimeMessageUtil setMessage(String message, Boolean isHtml) throws MessagingException {
        this.helper.setText(message,isHtml);
        return this;
    }

    public boolean send() {
        try {
            this.sender.send(this.message);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
