package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlContentMapper {
    long countByExample(TbCrawlContentExample example);

    int deleteByExample(TbCrawlContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlContent record);

    int insertSelective(TbCrawlContent record);

    List<TbCrawlContent> selectByExampleWithBLOBs(TbCrawlContentExample example);

    List<TbCrawlContent> selectByExample(TbCrawlContentExample example);

    TbCrawlContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlContent record, @Param("example") TbCrawlContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TbCrawlContent record, @Param("example") TbCrawlContentExample example);

    int updateByExample(@Param("record") TbCrawlContent record, @Param("example") TbCrawlContentExample example);

    int updateByPrimaryKeySelective(TbCrawlContent record);

    int updateByPrimaryKeyWithBLOBs(TbCrawlContent record);

    int updateByPrimaryKey(TbCrawlContent record);
}