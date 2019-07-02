package cn.com.xiaofabo.hca.epainfocollector.mail;

import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cn.com.xiaofabo.hca.epainfocollector.cache.LocalCache;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine engine;

	private static final String FROM = "718586216@qq.com";

	public static final String DICT_KEY = "mail-notify";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendBaoZhiHtmlMail(String[] tos, String subject, String htmlName) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(FROM);
			helper.setTo(tos);
			helper.setSubject(subject);

			Context context = new Context();
			context.setVariable("date", LocalDate.now().toString());
			context.setVariable("images", "");
			helper.setText(engine.process(htmlName, context), true);
			mailSender.send(message);
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！{}", e.getMessage());
		}
	}

	public void sendSimpleEmail(String[] tos, String subject, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(FROM);
		simpleMailMessage.setTo(tos);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);

		mailSender.send(simpleMailMessage);
	}
}
