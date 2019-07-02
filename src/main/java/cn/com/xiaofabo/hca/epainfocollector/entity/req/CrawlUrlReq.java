package cn.com.xiaofabo.hca.epainfocollector.entity.req;

import java.io.Serializable;
import java.util.Date;

public class CrawlUrlReq implements Serializable {

    private String channel;

    private String postTime;

    private String title;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}