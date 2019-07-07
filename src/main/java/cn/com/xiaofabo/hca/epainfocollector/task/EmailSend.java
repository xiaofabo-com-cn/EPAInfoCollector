package cn.com.xiaofabo.hca.epainfocollector.task;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.CrawService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class EmailSend implements SchedulingConfigurer {

	public static final String DICT_KEY = "mail-send-time";



	public static String emailSendCron;

	@Autowired
	PersistenceService persistenceService;

	@Autowired
	MailService mailService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//项目部署时，会在这里执行一次，从数据库拿到cron表达式
		List<TbCrawlDict> dicts = persistenceService.getDictByKey(DICT_KEY);
		if (CollectionUtils.isEmpty(dicts)){
			return;
		}
		emailSendCron = dicts.parallelStream().findFirst().get().getdValue();

		Runnable task = new Runnable() {
			@Override
			public void run() {
				//任务逻辑代码部分.
				mailService.sendMailTask();
			}
		};
		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				//任务触发，可修改任务的执行周期.
				//每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间
				List<TbCrawlDict> dicts = persistenceService.getDictByKey(DICT_KEY);
				if (!CollectionUtils.isEmpty(dicts)){
					emailSendCron = dicts.parallelStream().findFirst().get().getdValue();
				}
				CronTrigger trigger = new CronTrigger(emailSendCron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};
		taskRegistrar.addTriggerTask(task, trigger);
	}

}

