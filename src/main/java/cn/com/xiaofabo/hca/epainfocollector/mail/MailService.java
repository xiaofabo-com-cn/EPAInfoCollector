package cn.com.xiaofabo.hca.epainfocollector.mail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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

	public static final String DICT_MAIL_KEY = "mail-notify";

	public static final String DICT_MAIL_CRON_KEY = "mail-send-time";

	public static final String DICT_MAIL_SEND_LAST_TIME = "mail-last-time";

	public static final String DICT_MAIL_NO_SEND = "mail-no-send";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersistenceService persistenceService;

	public void sendHtmlMail(String[] tos, String subject, String htmlName, Object content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(FROM);
			helper.setTo(tos);
			helper.setSubject(subject);

			Context context = new Context();
			//context.setVariable("date", LocalDate.now().toString());
			context.setVariable("list", content);
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

	public void sendMailTask(){
		List<TbCrawlDict> dicts = persistenceService.getDictByKey(DICT_MAIL_SEND_LAST_TIME);
		String startTime = null;
		TbCrawlDict dict = null;
		if (CollectionUtils.isEmpty(dicts)){
			startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
		} else {
			dict = dicts.parallelStream().findFirst().get();
			if (StringUtils.isEmpty(dict.getdValue())){
				startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
			} else {
				startTime = dict.getdValue();
			}
		}

		Integer count = persistenceService.countCollect(startTime);

		List<TbCrawlDict> mailDicts = persistenceService.getDictByKey(DICT_MAIL_KEY);
		if (CollectionUtils.isEmpty(mailDicts)){
			logger.error("未配置通知email列表，发送邮件通知失败，如需技术支持请联系email：718586216@qq.com");
			return;
		}
		TbCrawlDict mailDict = mailDicts.parallelStream().findFirst().get();
		if (StringUtils.isEmpty(mailDict.getdValue())){
			logger.error("未配置通知email列表，发送邮件通知失败，如需技术支持请联系email：718586216@qq.com");
			return;
		}
		String[] tos = mailDict.getdValue().split(",");
		if (count > 0){

			sendSimpleEmail(tos, "环境局信息收集系统邮件通知", "已收集到"+count+"最新的信息，请前往系统查看!");

			if (dict == null){
				dict = new TbCrawlDict();
				dict.setdKey(DICT_MAIL_SEND_LAST_TIME);
			}
			dict.setdValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			persistenceService.editDict(dict);
		} else {
			List<TbCrawlDict> noSendDictList = persistenceService.getDictByKey(DICT_MAIL_NO_SEND);
			if (CollectionUtils.isEmpty(noSendDictList)){
				return;
			}
			TbCrawlDict noSendDict = noSendDictList.parallelStream().findFirst().get();
			if (Constant.YES.equals(noSendDict.getdValue())){
				sendSimpleEmail(tos, "环境局信息收集系统邮件通知", "无新消息!");
			}
		}
	}

	public static void main(String[] args){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date()));;
	}
}
