package cn.com.xiaofabo.hca.epainfocollector.entity;

public class TbCrawlContentWithBLOBs extends TbCrawlContent {
    private byte[] title;

    private byte[] bodyContent;

    public byte[] getTitle() {
        return title;
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public byte[] getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(byte[] bodyContent) {
        this.bodyContent = bodyContent;
    }
}