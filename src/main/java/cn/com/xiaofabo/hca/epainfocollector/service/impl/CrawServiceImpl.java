package cn.com.xiaofabo.hca.epainfocollector.service.impl;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlRule;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import cn.com.xiaofabo.hca.epainfocollector.service.CrawService;
import com.geccocrawler.gecco.spider.HrefBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawServiceImpl implements CrawService {

    @Autowired
    PersistenceService persistenceService;

    @Override
    public void run() {
        List<TbCrawlRule> tbCrawlRules = persistenceService.getAllCrawRule();
        if (CollectionUtils.isEmpty(tbCrawlRules)){
            return;
        }

        for (TbCrawlRule tbCrawlRule : tbCrawlRules){
            String[] matchUrls = tbCrawlRule.getMatchUrl().split(",");
            //初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
            GeccoEngine ge =
                    GeccoEngine.create("cn.com.xiaofabo.hca.epainfocollector.craw").debug(false).loop(true).interval(2000).engineStart();

            Class<?> listRule = DynamicGecco
                    .html()
                    .gecco(matchUrls, "hrefPipeline")
                    .requestField("request").request().build()
                    .listField("hrefList", HrefBean.class).csspath(tbCrawlRule.getHref()).text(false).build()
                    .listField("postTimeList",Object.class).csspath(tbCrawlRule.getPostTime()).build()
                    .stringField("title").csspath(tbCrawlRule.getTitle()).text().build()
                    .stringField("bodyContent").csspath(tbCrawlRule.getBodyContent()).html(false).build()
                    .stringField("bodyFileHref").csspath(tbCrawlRule.getBodyFile()).href().build()
                    .stringField("bodyFileName").csspath(tbCrawlRule.getBodyFile()).text().build()
                    .loadClass();
            //注册规则
            ge.register(listRule);

            //加入初始请求，爬虫引擎开始工作
            ge.getScheduler().into(new HttpGetRequest(tbCrawlRule.getStartUrl()));

        }


    }
}
