package cn.com.xiaofabo.hca.epainfocollector.craw;

import cn.com.xiaofabo.hca.epainfocollector.context.ApplicationContextProvider;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.service.impl.PersistenceServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.SpiderBean;
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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PipelineName("hrefPipeline")
public class HrefPipeline extends JsonPipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //创建一个固定线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    PersistenceService persistenceService = ApplicationContextProvider.getBean(PersistenceService.class);

    private CloseableHttpClient httpClient;
    {
        RequestConfig clientConfig = RequestConfig.custom().setRedirectsEnabled(false).build();
        PoolingHttpClientConnectionManager syncConnectionManager = new PoolingHttpClientConnectionManager();
        syncConnectionManager.setMaxTotal(1000);
        syncConnectionManager.setDefaultMaxPerRoute(50);
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(clientConfig).setConnectionManager(syncConnectionManager).build();
    }

    public void process(JSONObject jsonObject) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jsonObject.getJSONObject("request"));
        JSONArray hrefList = jsonObject.getJSONArray("hrefList");
        JSONArray postTimeList = jsonObject.getJSONArray("postTimeList");

        List<TbCrawlUrl> tbUrls = new ArrayList<>();
        for (int i = 0; i < hrefList.size(); i++){
            String aUrl = hrefList.getJSONObject(i).getString("url");
            String aTitle = hrefList.getJSONObject(i).getString("title");
            TbCrawlUrl tbCrawlUrl = new TbCrawlUrl();
            tbCrawlUrl.setStartUrl(currRequest.getUrl());
            tbCrawlUrl.setTitle(aTitle);
            tbCrawlUrl.setUrl(aUrl);
            tbCrawlUrl.setPostTime(postTimeList.getString(i).toString());
            tbUrls.add(tbCrawlUrl);

            SchedulerContext.into(currRequest.subRequest(aUrl));
        }
        if (!CollectionUtils.isEmpty(tbUrls)){

            executor.execute(()->{
                persistenceService.batchInsertUrl(tbUrls);
            });
        }

        String title = jsonObject.getString("title");
        String bodyContent = jsonObject.getString("bodyContent");
        String bodyFileHref = jsonObject.getString("bodyFileHref");
        String bodyFileName = jsonObject.getString("bodyFileName");

        if (!StringUtils.isEmpty(title)){
            TbCrawlContent tbCrawlContent = new TbCrawlContent();

            tbCrawlContent.setStartUrl(currRequest.getUrl());
            tbCrawlContent.setTitle(title);
            tbCrawlContent.setBodyFileName(bodyFileName);
            tbCrawlContent.setBodyFileUrl(bodyFileHref);
            tbCrawlContent.setBodyContent(bodyContent.getBytes());

            executor.execute(()->{
                persistenceService.batchInsertContent(Arrays.asList(tbCrawlContent));

                if (!StringUtils.isEmpty(bodyFileHref)){
                    System.out.println("附件地址：" + bodyFileHref);
                    HttpRequestBase request = new HttpGet(bodyFileHref);
                    try {
                        HttpClientContext context = HttpClientContext.create();
                        org.apache.http.HttpResponse response = httpClient.execute(request, context);
                        String fileName = bodyFileHref.trim().substring(bodyFileHref.lastIndexOf("/") + 1);
                        FileUtils.copyInputStreamToFile(response.getEntity().getContent(),
                                new File("D:/file/" + fileName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        request.releaseConnection();
                    }
                }
            });
        }




    }


    public static void main(String[] args){

    }
}
