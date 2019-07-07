package cn.com.xiaofabo.hca.epainfocollector.service.impl;

import cn.com.xiaofabo.hca.epainfocollector.cache.LocalCache;
import cn.com.xiaofabo.hca.epainfocollector.craw.entity.MyHrefBean;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlRule;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import cn.com.xiaofabo.hca.epainfocollector.service.CrawService;
import com.geccocrawler.gecco.spider.HrefBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawServiceImpl implements CrawService {

    @Autowired
    PersistenceService persistenceService;

    @Autowired
    MailService mailService;

    @Override
    public void runTask() {
        List<TbCrawlRule> tbCrawlRules = persistenceService.getAllCrawRule(true);
        if (CollectionUtils.isEmpty(tbCrawlRules)){
            return;
        }

        for (TbCrawlRule tbCrawlRule : tbCrawlRules){
            String[] matchUrls = tbCrawlRule.getMatchUrl().split(",");
            //初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
            GeccoEngine ge =  GeccoEngine.create("cn.com.xiaofabo.hca.epainfocollector.craw")
                    .debug(false)
                    .interval(2000).engineStart();


            Class<?> listRule = DynamicGecco
                    .html()
                    .gecco(matchUrls, "hrefPipeline")
                    .requestField("request").request().build()
                    .listField("hrefList", MyHrefBean.class).csspath(tbCrawlRule.getHref()).text(false).build()
                    .listField("postTimeList",Object.class).csspath(tbCrawlRule.getPostTime()).build()
                    .stringField("title").csspath(tbCrawlRule.getTitle()).html(false).build()
                    .stringField("bodyContent").csspath(tbCrawlRule.getBodyContent()).html(false).build()
                    .listField("bodyFileHref", MyHrefBean.class).csspath(tbCrawlRule.getBodyFile()).text().build()
                    .listField("bodyFileName",Object.class).csspath(tbCrawlRule.getBodyFile()).text().build()
                    .loadClass();
            //注册规则
            ge.register(listRule);

            //加入初始请求，爬虫引擎开始工作
            ge.getScheduler().into(new HttpGetRequest(tbCrawlRule.getStartUrl()));
        }
    }

    @Override
    public void runManual(String channel, String keyword, String emails) {
        List<TbCrawlRule> tbCrawlRules = persistenceService.getCrawRuleByChannel(channel);

        String key = JSON.toJSONString(tbCrawlRules)+"$"+keyword+"$"+emails;
        Object paraObj = LocalCache.get(key);
        LocalCache.set(key,null);
        if (!StringUtils.isEmpty(paraObj)) {
            String[] paraStr = paraObj.toString().split("\\$");
            mailService.sendHtmlMail(paraStr[2].split(","), "环境局信息收集系统邮件通知", "/mailTemplate", paraObj);
            LocalCache.set(key,null);
            return;
        }

        for (TbCrawlRule tbCrawlRule : tbCrawlRules){
            String[] matchUrls = tbCrawlRule.getMatchUrl().split(",");
            //初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
            GeccoEngine ge =  GeccoEngine.create("cn.com.xiaofabo.hca.epainfocollector.craw")
                    .debug(false)
                    .interval(2000).engineStart();


            Class<?> manualRule = DynamicGecco
                    .html()
                    .gecco(matchUrls, "manualPipeline")
                    .requestField("request").request().build()
                    .listField("hrefList", MyHrefBean.class).csspath(tbCrawlRule.getHref()).text(false).build()
                    .listField("postTimeList",Object.class).csspath(tbCrawlRule.getPostTime()).build()
                    .loadClass();
            //注册规则
            ge.register(manualRule);

            //加入初始请求，爬虫引擎开始工作
            ge.getScheduler().into(new HttpGetRequest(tbCrawlRule.getStartUrl()));
        }
    }
}
