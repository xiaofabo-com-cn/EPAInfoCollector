package cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository;

import java.util.List;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.Resource;


/**
 * @author Jonsy
 *
 */
public interface ResourceRepository {

    void add(Resource resource);

    void update(Resource resource);

    Resource get(String code);

    List<Resource> list();

    void remove(String code);

    void switchStatus(String code, boolean disabled);

    List<Resource> listByRole(String roleId);

    List<Resource> getEnableResources();



}
