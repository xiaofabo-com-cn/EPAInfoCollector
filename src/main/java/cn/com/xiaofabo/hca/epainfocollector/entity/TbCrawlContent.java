package cn.com.xiaofabo.hca.epainfocollector.entity;

import java.util.Date;

public class TbCrawlContent {
    private Integer id;

    private String startUrl;

    private String title;

    private String bodyFileName;

    private String bodyFileUrl;

    private Date createTime;

    private Date updateTime;

    private byte[] bodyContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyFileName() {
        return bodyFileName;
    }

    public void setBodyFileName(String bodyFileName) {
        this.bodyFileName = bodyFileName;
    }

    public String getBodyFileUrl() {
        return bodyFileUrl;
    }

    public void setBodyFileUrl(String bodyFileUrl) {
        this.bodyFileUrl = bodyFileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(byte[] bodyContent) {
        this.bodyContent = bodyContent;
    }
}