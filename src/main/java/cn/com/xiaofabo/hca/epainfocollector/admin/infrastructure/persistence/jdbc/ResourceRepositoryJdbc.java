package cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.Resource;
import cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository.ResourceRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class ResourceRepositoryJdbc implements ResourceRepository{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(Resource tb_crawl_resource) {
        jdbcTemplate.update("INSERT INTO tb_crawl_resource (id,title,disabled,url,description) VALUES (?,?,?,?,?)",tb_crawl_resource.getId(),tb_crawl_resource.getTitle(),tb_crawl_resource.isDisabled()?1:0,tb_crawl_resource.getUrl(),tb_crawl_resource.getDescription());
    }

    @Override
    public void update(Resource tb_crawl_resource) {
        jdbcTemplate.update("UPDATE tb_crawl_resource SET title=?,disabled=?,url=?,description=? WHERE `id`=?",tb_crawl_resource.getTitle(),tb_crawl_resource.isDisabled()?1:0,tb_crawl_resource.getUrl(),tb_crawl_resource.getDescription(),tb_crawl_resource.getId());
    }

    @Override
    public Resource get(String id) {
        return jdbcTemplate.queryForObject("select * from tb_crawl_resource where id=?",BeanPropertyRowMapper.newInstance(Resource.class),id);
    }

    @Override
    public List<Resource> list() {
        return jdbcTemplate.query("select * from tb_crawl_resource",BeanPropertyRowMapper.newInstance(Resource.class));
    }

    @Override
    public void remove(String id) {
        jdbcTemplate.update("DELETE FROM tb_crawl_resource WHERE id=?",id);
    }

    public void switchStatus(String id,boolean disabled){
        jdbcTemplate.update("update tb_crawl_resource SET disabled=? WHERE id=?",disabled?1:0,id);
    }


    @Override
    public List<Resource> listByRole(String roleId) {
        return jdbcTemplate.query("select re.* from  role_resource rr  join tb_crawl_resource re on re.id=rr.resource_id where rr.role_id=?", BeanPropertyRowMapper.newInstance(Resource.class), roleId);
    }


//    @Override
//    public List<Resource> getUrlResources(String userId) {
//        return jdbcTemplate.query("select r.* from tb_crawl_resource r join role_resource rr on r.id=rr.resource_id join user_role ur on rr.role_id=ur.role_id where ur.uid=?",BeanPropertyRowMapper.newInstance(Resource.class),userId);
//    }

    @Override
    public List<Resource> getEnableResources() {
        return jdbcTemplate.query("select * from tb_crawl_resource WHERE disabled=0",BeanPropertyRowMapper.newInstance(Resource.class));

    }
}
