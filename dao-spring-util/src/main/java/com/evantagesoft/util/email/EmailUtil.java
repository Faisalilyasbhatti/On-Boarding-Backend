package com.evantagesoft.util.email;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.validator.constraints.Email;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Nand Khatri
 * @version 1.0
 * @date 3/16/2021
 */
@Component
public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private VelocityEngine velocityEngine;

    public String sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setFrom("nand.khatri@evantagesoft.com");
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return "Success";
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            return "System error";
        }
    }
    
    
    public void sendWellcomeEmail(String toEmail, String subject, String fullName) {
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nand.khatri@evantagesoft.com");
		message.setTo(toEmail);
		message.setSubject(subject);
 
		Template template = velocityEngine.getTemplate("./template/welcomeEmailTemplate.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("fullName", fullName);
		StringWriter writer = new StringWriter();
		template.merge(velocityContext, writer);
		message.setText(writer.toString());
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({ "rawtypes", "unchecked" })
            public void prepare(MimeMessage mimeMessage) throws Exception {
                 MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                 message.setTo(toEmail);
                 message.setFrom(new InternetAddress("nand.khatri@evantagesoft.com") );
                 message.setSubject(subject);
                 message.setSentDate(new Date());
                 Map model = new HashMap();
                 model.put("subject", subject);
                 model.put("fullName", fullName);
                  
                 String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "./template/welcomeEmailTemplate.vm", "UTF-8", model);
                 message.setText(text, true);
              }
           };
           mailSender.send(preparator);
    }
    
    
    public void sendOtpEmail(String toEmail, String subject, String otp, String fullName) {
    	SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nand.khatri@evantagesoft.com");
		message.setTo(toEmail);
		message.setSubject(subject);
 
		Template template = velocityEngine.getTemplate("./template/otpEmailTemplate.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("fullName", fullName);
		StringWriter writer = new StringWriter();
		template.merge(velocityContext, writer);
		message.setText(writer.toString());
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({ "rawtypes", "unchecked" })
            public void prepare(MimeMessage mimeMessage) throws Exception {
                 MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                 message.setTo(toEmail);
                 message.setFrom(new InternetAddress("nand.khatri@evantagesoft.com") );
                 message.setSubject(subject);
                 message.setSentDate(new Date());
                 Map model = new HashMap();
                 model.put("subject", subject);
                 model.put("fullName", fullName);
                 model.put("toEmail", toEmail);
                 model.put("otp", otp);
                  
                 String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "./template/otpEmailTemplate.vm", "UTF-8", model);
                 message.setText(text, true);
              }
           };
           mailSender.send(preparator);
    }
    
}
