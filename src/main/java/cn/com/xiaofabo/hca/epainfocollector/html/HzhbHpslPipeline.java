package cn.com.xiaofabo.hca.epainfocollector.html;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PipelineName(value="commonPipelines")
public class HzhbHpslPipeline implements Pipeline<Hzhb_hpsl> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void process(Hzhb_hpsl hzhb_hpsl) {
        logger.error("get content : {}", JSON.toJSONString(hzhb_hpsl));
    }
}
