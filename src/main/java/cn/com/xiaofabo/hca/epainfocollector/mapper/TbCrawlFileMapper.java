package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFile;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlFileMapper {
    long countByExample(TbCrawlFileExample example);

    int deleteByExample(TbCrawlFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlFile record);

    int insertSelective(TbCrawlFile record);

    List<TbCrawlFile> selectByExample(TbCrawlFileExample example);

    TbCrawlFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlFile record, @Param("example") TbCrawlFileExample example);

    int updateByExample(@Param("record") TbCrawlFile record, @Param("example") TbCrawlFileExample example);

    int updateByPrimaryKeySelective(TbCrawlFile record);

    int updateByPrimaryKey(TbCrawlFile record);
}