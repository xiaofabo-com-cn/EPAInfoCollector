package cn.com.xiaofabo.hca.epainfocollector.service.impl;

import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.entity.*;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.entity.resp.CrawlUrlResp;
import cn.com.xiaofabo.hca.epainfocollector.mapper.*;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.task.ExpireData;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    @Autowired
    TbCrawlFileMapper tbCrawlFileMapper;

    @Override
    public List<TbCrawlRule> getAllCrawRule(boolean hasCondition) {
        TbCrawlRuleExample tbCrawlRuleExample = new TbCrawlRuleExample();
        if (hasCondition){
            tbCrawlRuleExample.createCriteria().andIsDeleteEqualTo(Constant.DELETE_NO);
        }
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
    public TbCrawlContentWithBLOBs urlDetail(String url) {
        TbCrawlContentExample tbCrawlContentExample = new TbCrawlContentExample();
        tbCrawlContentExample.createCriteria().andStartUrlEqualTo(url);
        List<TbCrawlContentWithBLOBs> tbCrawlContentList = tbCrawlContentMapper.selectByExampleWithBLOBs(tbCrawlContentExample);
        if (CollectionUtils.isEmpty(tbCrawlContentList)){
            return null;
        }
        return tbCrawlContentList.parallelStream().findFirst().get();
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

    @Override
    public Integer changeRuleStatus(TbCrawlRule tbCrawlRule) {
        return tbCrawlRuleMapper.updateByPrimaryKeySelective(tbCrawlRule);
    }

    @Override
    public Integer countCollect(String startTime) {
        return vTbMapper.countCollect(startTime);
    }

    @Override
    public Integer batchInsertFile(List<TbCrawlFile> tbCrawlFiles) {
        return vTbMapper.batchInsertFile(tbCrawlFiles);
    }

    @Override
    public List<TbCrawlFile> urlFile(String url) {
        TbCrawlFileExample tbCrawlFileExample = new TbCrawlFileExample();
        tbCrawlFileExample.createCriteria().andStartUrlEqualTo(url);
        return tbCrawlFileMapper.selectByExample(tbCrawlFileExample);
    }

    @Override
    public TbCrawlFile getFileById(Integer id) {
        return tbCrawlFileMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteExpireData() {
        List<TbCrawlDict> expireParaList = getDictByKey(ExpireData.DICT_EXPIRE_DAY_KEY);
        if (CollectionUtils.isEmpty(expireParaList)){
            return;
        }
        TbCrawlDict expirePara = expireParaList.parallelStream().findFirst().get();
        if (StringUtils.isEmpty(expirePara.getdValue()) || !CommonUtil.isNumber(expirePara.getdValue())){
            return;
        }
        Integer expireDay = Integer.parseInt(expirePara.getdValue());
        List<TbCrawlFile> tbCrawlFiles = vTbMapper.selectExpireFile(expireDay);
        if (CollectionUtils.isEmpty(tbCrawlFiles)){
            return;
        }
        //先删除过期附件
        try {
            for (TbCrawlFile file : tbCrawlFiles){
                CommonUtil.delFile(Constant.FILE_PATH, file.getFileMd5());
            }
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        //删除数据库记录
        vTbMapper.deleteExpireContent(expireDay);
        vTbMapper.deleteExpireUrl(expireDay);
        vTbMapper.deleteExpireFile(expireDay);
    }

    @Override
    public TbCrawlFile queryFileByUrl(String fileUrl) {
        return vTbMapper.queryFileByUrl(fileUrl);
    }

    @Override
    public List<TbCrawlRule> getCrawRuleByChannel(String channel) {
        TbCrawlRuleExample tbCrawlRuleExample= new TbCrawlRuleExample();
        tbCrawlRuleExample.createCriteria().andChannelEqualTo(channel);
        return tbCrawlRuleMapper.selectByExample(tbCrawlRuleExample);
    }
}
