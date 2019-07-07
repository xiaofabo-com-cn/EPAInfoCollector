package cn.com.xiaofabo.hca.epainfocollector.service;

import cn.com.xiaofabo.hca.epainfocollector.entity.*;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.entity.resp.CrawlUrlResp;

import java.util.List;

public interface PersistenceService {

    List<TbCrawlRule> getAllCrawRule(boolean hasCondition);

    Integer batchInsertUrl(List<TbCrawlUrl> tbCrawlUrls);

    Integer batchInsertContent(List<TbCrawlContent> tbCrawlContents);

    List<CrawlUrlResp> urlList(CrawlUrlReq crawlUrlReq);

    TbCrawlContent urlDetail(String url);

    TbCrawlUrl getUrlById(Integer id);

    List<TbCrawlDict> getDictByKey(String key);

    void editDict(TbCrawlDict tbCrawlDict);

    Integer changeRuleStatus(TbCrawlRule tbCrawlRule);

    Integer countCollect(String startTime);

    Integer batchInsertFile(List<TbCrawlFile> tbCrawlFiles);

    List<TbCrawlFile> urlFile(String url);

    TbCrawlFile getFileById(Integer id);

    void deleteExpireData();

    TbCrawlFile queryFileByUrl(String fileUrl);

    List<TbCrawlRule> getCrawRuleByChannel(String channel);
}
