package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFile;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.entity.resp.CrawlUrlResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VTBMapper {

    Integer batchInsertUrl(List<TbCrawlUrl> list);

    Integer batchInsertContent(List<TbCrawlContent> list);

    List<CrawlUrlResp> urlList(@Param("crawlUrlReq") CrawlUrlReq crawlUrlReq);

    Integer countCollect(@Param("startTime")String startTime);

    Integer batchInsertFile(List<TbCrawlFile> list);

    List<TbCrawlFile> selectExpireFile(@Param("expireDay")Integer expireDay);

    void deleteExpireUrl(@Param("expireDay")Integer expireDay);

    void deleteExpireContent(@Param("expireDay")Integer expireDay);

    void deleteExpireFile(@Param("expireDay")Integer expireDay);

    TbCrawlFile queryFileByUrl(@Param("fileUrl")String fileUrl);
}
