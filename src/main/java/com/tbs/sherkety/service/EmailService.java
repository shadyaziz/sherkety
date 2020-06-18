package com.tbs.sherkety.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender emailSender;

  public void sendRegistrationEmail(String userEmail, String url) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(userEmail);
    message.setSubject("Welcome to Sherkety");
    message.setText("please verify your email by clicking the following link " + url);
    emailSender.send(message);
  }
}
