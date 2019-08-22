package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContentExample;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlContentMapper {
    long countByExample(TbCrawlContentExample example);

    int deleteByExample(TbCrawlContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlContentWithBLOBs record);

    int insertSelective(TbCrawlContentWithBLOBs record);

    List<TbCrawlContentWithBLOBs> selectByExampleWithBLOBs(TbCrawlContentExample example);

    List<TbCrawlContent> selectByExample(TbCrawlContentExample example);

    TbCrawlContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlContentWithBLOBs record, @Param("example") TbCrawlContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TbCrawlContentWithBLOBs record, @Param("example") TbCrawlContentExample example);

    int updateByExample(@Param("record") TbCrawlContent record, @Param("example") TbCrawlContentExample example);

    int updateByPrimaryKeySelective(TbCrawlContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbCrawlContentWithBLOBs record);

    int updateByPrimaryKey(TbCrawlContent record);
}