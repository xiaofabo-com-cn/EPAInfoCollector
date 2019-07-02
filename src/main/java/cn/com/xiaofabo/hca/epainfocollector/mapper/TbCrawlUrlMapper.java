package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlUrlMapper {
    long countByExample(TbCrawlUrlExample example);

    int deleteByExample(TbCrawlUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlUrl record);

    int insertSelective(TbCrawlUrl record);

    List<TbCrawlUrl> selectByExample(TbCrawlUrlExample example);

    TbCrawlUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlUrl record, @Param("example") TbCrawlUrlExample example);

    int updateByExample(@Param("record") TbCrawlUrl record, @Param("example") TbCrawlUrlExample example);

    int updateByPrimaryKeySelective(TbCrawlUrl record);

    int updateByPrimaryKey(TbCrawlUrl record);
}