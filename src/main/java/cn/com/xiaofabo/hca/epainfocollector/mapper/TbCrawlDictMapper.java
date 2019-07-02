package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlDictMapper {
    long countByExample(TbCrawlDictExample example);

    int deleteByExample(TbCrawlDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlDict record);

    int insertSelective(TbCrawlDict record);

    List<TbCrawlDict> selectByExample(TbCrawlDictExample example);

    TbCrawlDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlDict record, @Param("example") TbCrawlDictExample example);

    int updateByExample(@Param("record") TbCrawlDict record, @Param("example") TbCrawlDictExample example);

    int updateByPrimaryKeySelective(TbCrawlDict record);

    int updateByPrimaryKey(TbCrawlDict record);
}