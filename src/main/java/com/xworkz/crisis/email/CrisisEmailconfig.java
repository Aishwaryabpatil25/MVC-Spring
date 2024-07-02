package com.xworkz.crisis.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class CrisisEmailconfig {

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl emailSender=new JavaMailSenderImpl();
        emailSender.setHost("smtp.gmail.com");
        emailSender.setPort(587);
        emailSender.setUsername("aishupatil451@gmail.com");
        emailSender.setPassword("gmin nnxr lpvo pkoi");
        Properties properties=emailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return emailSender;
    }
}
