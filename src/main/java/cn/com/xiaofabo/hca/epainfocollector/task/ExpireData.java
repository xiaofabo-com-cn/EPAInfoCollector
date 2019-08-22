package cn.com.xiaofabo.hca.epainfocollector.task;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
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

	@Value("${custom.expire}")
	private String deleteExpireDataUrl;

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
				//persistenceService.deleteExpireData();
				deleteExpireDateByES();
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

	private void deleteExpireDateByES(){
		List<TbCrawlDict> expireParaList = persistenceService.getDictByKey(ExpireData.DICT_EXPIRE_DAY_KEY);
		if (CollectionUtils.isEmpty(expireParaList)){
			return;
		}
		TbCrawlDict expirePara = expireParaList.parallelStream().findFirst().get();
		if (StringUtils.isEmpty(expirePara.getdValue()) || !CommonUtil.isNumber(expirePara.getdValue())){
			return;
		}
		Integer expireDay = Integer.parseInt(expirePara.getdValue());
		String startTime = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(new Date(),
				CommonUtil.unAbs(expireDay)));
		CommonUtil.getHttpResult(deleteExpireDataUrl + startTime);
	}


}

