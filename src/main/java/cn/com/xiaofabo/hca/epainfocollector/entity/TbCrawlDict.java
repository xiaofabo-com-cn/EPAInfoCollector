package cn.com.xiaofabo.hca.epainfocollector.entity;

import java.util.Date;

public class TbCrawlDict {
    private Integer id;

    private String dKey;

    private String dValue;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdKey() {
        return dKey;
    }

    public void setdKey(String dKey) {
        this.dKey = dKey;
    }

    public String getdValue() {
        return dValue;
    }

    public void setdValue(String dValue) {
        this.dValue = dValue;
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
}