package com.qlcx.common.utils;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {
	public  void sendEmail(List<String> email, JavaMailSender javaMailSender) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			message.setFrom(new InternetAddress("myle10032001@gmail.com", "TreeManager"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); 
		for (String e : email) {
			helper.setTo(e);
			helper.setSubject("THÔNG BÁO CÔNG VIỆC MỚI");
			helper.setText("Bạn đã có công việc mới vui lòng kiểm tra hệ thống...",true);
	        // Send Message!
	        javaMailSender.send(message);
		}
		

    }
}
