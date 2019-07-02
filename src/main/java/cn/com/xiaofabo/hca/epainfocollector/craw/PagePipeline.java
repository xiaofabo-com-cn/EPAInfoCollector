package cn.com.xiaofabo.hca.epainfocollector.craw;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.StartRequestList;
import com.geccocrawler.gecco.spider.SpiderBean;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;

@PipelineName("pagePipeline")
public class PagePipeline implements Pipeline<SpiderBean> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PagePipeline() {
    }

    public void process(SpiderBean bean) {
        Map map = Collections.EMPTY_MAP;
        try {
            map = JSON.parseObject(JSON.toJSONString(bean), Map.class);
        } catch (Exception e){
            logger.error("parse error SpiderBean:{}, exception:{}",JSON.toJSONString(bean), e);
        }
        String request = MapUtils.getString(map,"request","");
        String currentPage = MapUtils.getString(map,"current_page","");;
        String totalPage = MapUtils.getString(map,"total_page","");;
        if (StringUtils.isEmpty(request) || StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(totalPage)){
            logger.warn("参数无效,跳出分页爬取 SpiderBean:{}",map);
            return;
        }
        StartRequestList startRequestList = JSON.parseObject(request, StartRequestList.class);
        logger.info("分页链接处理pipeline, url={}", startRequestList.getUrl());
        if (StringUtils.isEmpty(startRequestList.getUrl())){
            logger.warn("参数无效,跳出分页爬取 url:{}",startRequestList.getUrl());
            return;
        }

    }
}
