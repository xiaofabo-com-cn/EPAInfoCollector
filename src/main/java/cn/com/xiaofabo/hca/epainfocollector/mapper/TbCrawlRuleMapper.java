package cn.com.xiaofabo.hca.epainfocollector.mapper;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlRule;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCrawlRuleMapper {
    long countByExample(TbCrawlRuleExample example);

    int deleteByExample(TbCrawlRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCrawlRule record);

    int insertSelective(TbCrawlRule record);

    List<TbCrawlRule> selectByExample(TbCrawlRuleExample example);

    TbCrawlRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCrawlRule record, @Param("example") TbCrawlRuleExample example);

    int updateByExample(@Param("record") TbCrawlRule record, @Param("example") TbCrawlRuleExample example);

    int updateByPrimaryKeySelective(TbCrawlRule record);

    int updateByPrimaryKey(TbCrawlRule record);
}