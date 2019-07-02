package cn.com.xiaofabo.hca.epainfocollector.service;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlRule;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.entity.resp.CrawlUrlResp;

import java.util.List;

public interface PersistenceService {

    List<TbCrawlRule> getAllCrawRule();

    Integer batchInsertUrl(List<TbCrawlUrl> tbCrawlUrls);

    Integer batchInsertContent(List<TbCrawlContent> tbCrawlContents);

    List<CrawlUrlResp> urlList(CrawlUrlReq crawlUrlReq);

    TbCrawlContent urlDetail(String url);

    TbCrawlUrl getUrlById(Integer id);

    List<TbCrawlDict> getDictByKey(String key);

    void editDict(TbCrawlDict tbCrawlDict);
}
