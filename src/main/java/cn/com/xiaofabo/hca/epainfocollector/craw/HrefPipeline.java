package cn.com.xiaofabo.hca.epainfocollector.craw;

import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.context.ApplicationContextProvider;
import cn.com.xiaofabo.hca.epainfocollector.craw.entity.MyHrefBean;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContentWithBLOBs;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFile;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.service.impl.PersistenceServiceImpl;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;
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
        //适配下首页有文件的超链接
        List<MyHrefBean> myHrefBeans = new ArrayList<>();
        for (int i = 0; i < hrefList.size(); i++){
            String aUrl = hrefList.getJSONObject(i).getString("url");
            String aTitle = hrefList.getJSONObject(i).getString("title");
            String aText = hrefList.getJSONObject(i).getString("text");
            TbCrawlUrl tbCrawlUrl = new TbCrawlUrl();
            tbCrawlUrl.setStartUrl(currRequest.getUrl());
            tbCrawlUrl.setTitle(StringUtils.isEmpty(aTitle) ? aText : aTitle);
            tbCrawlUrl.setUrl(aUrl);
            tbCrawlUrl.setPostTime(CommonUtil.getSubDateString(postTimeList.getString(i)));
            tbUrls.add(tbCrawlUrl);

            String aUrlSuffix = aUrl.substring(aUrl.lastIndexOf(".")+1);
            if (Constant.ACCESS_FILE_TYPES.contains(aUrlSuffix.toLowerCase())){
                MyHrefBean myHrefBean = new MyHrefBean();
                myHrefBean.setText(StringUtils.isEmpty(aTitle) ? aText : aTitle);
                myHrefBean.setUrl(aUrl);
                myHrefBean.setTitle(aTitle);
                myHrefBeans.add(myHrefBean);
                continue;
            }

            SchedulerContext.into(currRequest.subRequest(aUrl));
        }
        if (!CollectionUtils.isEmpty(tbUrls)){
            executor.execute(()->{
                persistenceService.batchInsertUrl(tbUrls);
            });
        }

        String title = jsonObject.getString("title");
        String bodyContent = jsonObject.getString("bodyContent");
        JSONArray bodyFileHrefList = jsonObject.getJSONArray("bodyFileHref");
        JSONArray bodyFileNameList = jsonObject.getJSONArray("bodyFileName");

        if (bodyFileHrefList.isEmpty() || bodyFileHrefList.size() < 1){
            bodyFileHrefList = new JSONArray();
        }
        if (!CollectionUtils.isEmpty(myHrefBeans)){
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(myHrefBeans));
            bodyFileHrefList.addAll(array);
        }

        if (!StringUtils.isEmpty(bodyContent)){
            TbCrawlContentWithBLOBs tbCrawlContent = new TbCrawlContentWithBLOBs();
            tbCrawlContent.setStartUrl(currRequest.getUrl());
            tbCrawlContent.setTitle(title.getBytes());
            tbCrawlContent.setBodyContent(bodyContent.getBytes());

            executor.execute(()->{
                persistenceService.batchInsertContent(Arrays.asList(tbCrawlContent));
            });
        } else {
            //抓取详情失败
        }

        List<TbCrawlFile> tbCrawlFiles = new ArrayList<>();
        try {
            for (int i = 0; i < bodyFileHrefList.size(); i++) {
                String fileUrl = bodyFileHrefList.getJSONObject(i).getString("url");
                String fileTitle = bodyFileHrefList.getJSONObject(i).getString("title");
                if (StringUtils.isEmpty(fileUrl)){
                    continue;
                }

                String fileNameSuffix = fileUrl.substring(fileUrl.lastIndexOf(".")+1);
                if (!Constant.ACCESS_FILE_TYPES.contains(fileNameSuffix.toLowerCase())){
                    continue;
                }

                String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
                TbCrawlFile tbCrawlFile = new TbCrawlFile();
                tbCrawlFile.setStartUrl(currRequest.getUrl());
                tbCrawlFile.setFileName(fileName);
                String md5FileName = CommonUtil.md5(fileUrl);
                tbCrawlFile.setFileMd5((StringUtils.isEmpty(md5FileName)?"": md5FileName).concat(".").concat(fileNameSuffix));
                tbCrawlFile.setFileUrl(fileUrl);

                tbCrawlFiles.add(tbCrawlFile);
            }
        } catch (Exception e){
            logger.error("入库失败!");
            e.printStackTrace();
        }
        if (!CollectionUtils.isEmpty(tbCrawlFiles)){
            executor.execute(()->{
                persistenceService.batchInsertFile(tbCrawlFiles);
            });

            for (TbCrawlFile tbCrawlFile : tbCrawlFiles){
                executor.execute(()->{
                    String pathName = Constant.FILE_PATH;
                    String fileName = tbCrawlFile.getFileUrl().substring(tbCrawlFile.getFileUrl().lastIndexOf("/") + 1);
                    String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
                    String md5FileName = CommonUtil.md5(tbCrawlFile.getFileUrl());
                    String localFileName = (StringUtils.isEmpty(md5FileName)?"": md5FileName).concat(".").concat(fileNameSuffix);
                    File fileTemp = new File(pathName.concat(localFileName));
                    if (fileTemp.exists()){
                        return;
                    }
                    String encodeFileName = null;
                    try {
                        encodeFileName = URLEncoder.encode(fileName,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String urlPrefix = tbCrawlFile.getFileUrl().substring(0, tbCrawlFile.getFileUrl().lastIndexOf("/") + 1);
                    HttpRequestBase request = new HttpGet(urlPrefix.concat(encodeFileName));
                    try {
                        HttpClientContext context = HttpClientContext.create();
                        org.apache.http.HttpResponse response = httpClient.execute(request, context);

                        FileUtils.copyInputStreamToFile(response.getEntity().getContent(),
                                new File(pathName.concat(localFileName)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        request.releaseConnection();
                    }
                });
            }
        }
    }


    public static void main(String[] args){
        System.out.println(LocalDate.now().toString());
    }
}
