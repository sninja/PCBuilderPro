package com.cdac.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cdac.entity.User;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}") private String sender;
	String otpcheck;
	public void sendEmail(String toEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("pcbuillderpro@gmail.com");
		message.setTo(toEmail);
		otpcheck = generateOTP();
		message.setText("OTP for completing payment is " +  otpcheck);
		message.setSubject("PCBuilderPro payment verification");
		
		mailSender.send(message);
		
		System.out.println("Mail sent successfully");
	}
	
	public String get() {
		return otpcheck;
	}
	
	public String generateOTP() {
		/* Generating random OTP */
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		
		return generatedString;
	}
}
