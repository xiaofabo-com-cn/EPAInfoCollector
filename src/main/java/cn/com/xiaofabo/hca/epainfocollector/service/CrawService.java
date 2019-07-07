package cn.com.xiaofabo.hca.epainfocollector.service;

public interface CrawService {

    void runTask();

    void runManual(String channel, String keyword, String emails);

}
