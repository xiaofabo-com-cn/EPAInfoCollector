package cn.com.xiaofabo.hca.epainfocollector.craw;

import cn.com.xiaofabo.hca.epainfocollector.cache.LocalCache;
import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.context.ApplicationContextProvider;
import cn.com.xiaofabo.hca.epainfocollector.craw.entity.MyHrefBean;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFile;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PipelineName("manualPipeline")
public class ManualPipeline extends JsonPipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    MailService mailService = ApplicationContextProvider.getBean(MailService.class);

    public void process(JSONObject jsonObject) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jsonObject.getJSONObject("request"));
        JSONArray hrefList = jsonObject.getJSONArray("hrefList");
        JSONArray postTimeList = jsonObject.getJSONArray("postTimeList");

        Iterator iter = LocalCache.getCacheMap().entrySet().iterator();
        while (iter.hasNext()) {
            List<TbCrawlUrl> tbUrls = new ArrayList<>();
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String[] str = key.split("\\$");
            String ruleJson = str[0];
            String keyWord = str[1];
            String emails = str[2];
            for (int i = 0; i < hrefList.size(); i++){
                String aUrl = hrefList.getJSONObject(i).getString("url");
                String aTitle = hrefList.getJSONObject(i).getString("title");
                String aText = hrefList.getJSONObject(i).getString("text");

                if (ruleJson.contains(currRequest.getUrl()) && ((aTitle != null && aTitle.contains(keyWord)) || (aText != null && aText.contains(keyWord)))){
                    TbCrawlUrl tbCrawlUrl = new TbCrawlUrl();
                    tbCrawlUrl.setStartUrl(currRequest.getUrl());
                    tbCrawlUrl.setTitle(StringUtils.isEmpty(aTitle) ? aText : aTitle);
                    tbCrawlUrl.setUrl(aUrl);
                    tbCrawlUrl.setPostTime(CommonUtil.getSubDateString(postTimeList.getString(i)));
                    tbUrls.add(tbCrawlUrl);
                }
            }
            LocalCache.push(key, tbUrls);
            if (!CollectionUtils.isEmpty(tbUrls) && LocalCache.get(key) != null){
                mailService.sendHtmlMail(emails.split(","), "环境局信息收集系统邮件通知", "mailTemplate", tbUrls);
                LocalCache.remove(key);
            }
        }
    }
}
