package cn.com.xiaofabo.hca.epainfocollector.html;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/*@Gecco(matchUrl = "http://www1.huizhou.gov.cn/pages/cms/hzhbj/html/artList.html?sn=hzhbj&cataId=670646a0b4484f5884543667a19eba56", pipelines = {
        "consolePipeline","commonPipelines"},downloader="httpClientDownloader")*/
public class Hzhb_hpsl implements HtmlBean {

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath = "body > table:nth-child(5) > tbody > tr > td:nth-child(3) > table:nth-child(2) > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > div > div:nth-child(2) > span.current")
    private String currPage;

    @Text
    @HtmlField(cssPath = "body > table:nth-child(5) > tbody > tr > td:nth-child(3) > table:nth-child(2) > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > div > div:nth-child(2) > span:nth-child(1)")
    private String totalPage;

    @HtmlField(cssPath = "li.li_art_title > a")
    private List<HrefBean> titleHrefList;


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getCurrPage() {
        return currPage;
    }

    public void setCurrPage(String currPage) {
        this.currPage = currPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<HrefBean> getTitleHrefList() {
        return titleHrefList;
    }

    public void setTitleHrefList(List<HrefBean> titleHrefList) {
        this.titleHrefList = titleHrefList;
    }
}
