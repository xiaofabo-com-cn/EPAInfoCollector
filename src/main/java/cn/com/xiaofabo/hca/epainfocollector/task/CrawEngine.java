package cn.com.xiaofabo.hca.epainfocollector.task;

import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.CrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CrawEngine {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MailService mailService;
	@Autowired
	private CrawService crawService;

	//每小时整点执行一次
	@Scheduled(cron = "0 0 * * * ?")
	public void baozhiTask() {
		logger.error("task run!");
		/*if (!StringUtils.isEmpty(BaozhiCache.get("TASK-STATUS:" + LocalDate.now().toString()))) {

			if (StringUtils.isEmpty(BaozhiCache.get("TASK-EMAIL-SEND:" + LocalDate.now().toString()))) {
				mailService.sendBaoZhiHtmlMail(new String[] { "530412087@qq.com", "450715946@qq.com" }, "环球时报",
						"baozhiMailTemplate");
				BaozhiCache.set("TASK-EMAIL-SEND:" + LocalDate.now().toString(), true);
				BaozhiCache.set("HUANQIU-IMAGES", null);
			}

			return;
		}

		String url = "http://www.jdqu.com";
		String classpath = "com.hugui.crawler.html";
		HttpRequest request = new HttpGetRequest(url);
		request.setCharset("gbk");
		GeccoEngine.create().classpath(classpath).start(request).interval(2000).run();*/



		/*String url = "http://www1.huizhou.gov.cn/pages/cms/hzhbj/html/artList" +
				".html?sn=hzhbj&cataId=670646a0b4484f5884543667a19eba56";
		String classpath = "com.hugui.crawler.html";
		HttpRequest request = new HttpGetRequest(url);
		request.setCharset("gbk");
		GeccoEngine.create().classpath(classpath).start(request).interval(2000).run();*/
		crawService.run();

	}
}
