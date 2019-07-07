package cn.com.xiaofabo.hca.epainfocollector.craw.entity;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HrefBean;

public class MyHrefBean extends HrefBean {

    @Text
    @HtmlField(
            cssPath = "font"
    )
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
