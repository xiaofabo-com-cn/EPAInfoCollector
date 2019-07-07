package cn.com.xiaofabo.hca.epainfocollector.task;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
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
public class ExpireData implements SchedulingConfigurer {

	public static final String DICT_EXPIRE_DAY_KEY = "data-expire-day";

	public static final String DICT_EXPIRE_CRON_KEY = "data-expire-cron";

	public static String expireCron;

	@Autowired
	PersistenceService persistenceService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//项目部署时，会在这里执行一次，从数据库拿到cron表达式
		List<TbCrawlDict> dicts = persistenceService.getDictByKey(DICT_EXPIRE_CRON_KEY);
		if (CollectionUtils.isEmpty(dicts)){
			return;
		}
		expireCron = dicts.parallelStream().findFirst().get().getdValue();

		Runnable task = new Runnable() {
			@Override
			public void run() {
				//任务逻辑代码部分.
				persistenceService.deleteExpireData();
			}
		};
		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				//任务触发，可修改任务的执行周期.
				//每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间
				List<TbCrawlDict> dicts = persistenceService.getDictByKey(DICT_EXPIRE_CRON_KEY);
				if (!CollectionUtils.isEmpty(dicts)){
					expireCron = dicts.parallelStream().findFirst().get().getdValue();
				}
				CronTrigger trigger = new CronTrigger(expireCron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};
		taskRegistrar.addTriggerTask(task, trigger);
	}

}

