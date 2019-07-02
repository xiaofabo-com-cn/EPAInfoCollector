package cn.com.xiaofabo.hca.epainfocollector.service.impl;

import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.entity.*;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.entity.resp.CrawlUrlResp;
import cn.com.xiaofabo.hca.epainfocollector.mapper.*;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PersistenceServiceImpl implements PersistenceService {
    @Autowired
    TbCrawlRuleMapper tbCrawlRuleMapper;
    @Autowired
    VTBMapper vTbMapper;
    @Autowired
    TbCrawlUrlMapper tbCrawlUrlMapper;
    @Autowired
    TbCrawlContentMapper tbCrawlContentMapper;
    @Autowired
    TbCrawlDictMapper tbCrawlDictMapper;

    @Override
    public List<TbCrawlRule> getAllCrawRule() {
        TbCrawlRuleExample tbCrawlRuleExample = new TbCrawlRuleExample();
        tbCrawlRuleExample.createCriteria().andIsDeleteEqualTo(Constant.DELETE_NO);
        return tbCrawlRuleMapper.selectByExample(tbCrawlRuleExample);
    }

    @Override
    public Integer batchInsertUrl(List<TbCrawlUrl> tbCrawlUrls) {
        return vTbMapper.batchInsertUrl(tbCrawlUrls);
    }

    @Override
    public Integer batchInsertContent(List<TbCrawlContent> tbCrawlContents) {
        return vTbMapper.batchInsertContent(tbCrawlContents);
    }

    @Override
    public List<CrawlUrlResp> urlList(CrawlUrlReq crawlUrlReq) {
        return vTbMapper.urlList(crawlUrlReq);
    }

    @Override
    public TbCrawlContent urlDetail(String url) {
        TbCrawlContentExample tbCrawlContentExample = new TbCrawlContentExample();
        tbCrawlContentExample.createCriteria().andStartUrlEqualTo(url);
        return tbCrawlContentMapper.selectByExampleWithBLOBs(tbCrawlContentExample).parallelStream().findFirst().get();
    }

    @Override
    public TbCrawlUrl getUrlById(Integer id) {
        return tbCrawlUrlMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbCrawlDict> getDictByKey(String key) {
        TbCrawlDictExample example = new TbCrawlDictExample();
        example.createCriteria().andDKeyEqualTo(key);
        return tbCrawlDictMapper.selectByExample(example);
    }

    @Override
    public void editDict(TbCrawlDict tbCrawlDict) {
        if (tbCrawlDict.getId() == null){
            tbCrawlDictMapper.insertSelective(tbCrawlDict);
        } else {
            tbCrawlDictMapper.updateByPrimaryKeySelective(tbCrawlDict);
        }
    }


}
