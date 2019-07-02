package cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository;

import java.util.List;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.SysLog;


/**
 * @author Jonsy
 *
 */
public interface SysLogRepository {

    void add(SysLog sysLog);

    List<SysLog> list();

    void clear();
}
