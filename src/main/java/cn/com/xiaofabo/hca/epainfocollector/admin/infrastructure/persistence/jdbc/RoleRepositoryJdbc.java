package cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.Role;
import cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository.RoleRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class RoleRepositoryJdbc implements RoleRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(Role tb_crawl_role) {
       jdbcTemplate.update("INSERT tb_crawl_role (id,`name`,disabled,description) VALUES (?,?,?,?)",tb_crawl_role.getId(),tb_crawl_role.getName(),tb_crawl_role.isDisabled()?1:0,tb_crawl_role.getDescription());
    }

    @Override
    public void update(Role tb_crawl_role) {
        jdbcTemplate.update("update tb_crawl_role SET `name` =?,disabled=?,description=? WHERE id=?",tb_crawl_role.getName(),tb_crawl_role.isDisabled()?1:0,tb_crawl_role.getDescription(),tb_crawl_role.getId());
    }

    @Override
    public void updateMenus(String rid, List<String> mids) {
        jdbcTemplate.update("DELETE FROM tb_crawl_role_menu WHERE role_id=?", rid);
        if (!CollectionUtils.isEmpty(mids)) {
            jdbcTemplate.batchUpdate("INSERT tb_crawl_role_menu (role_id,menu_id) VALUES (?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, rid);
                    ps.setString(2, mids.get(i));
                }

                @Override
                public int getBatchSize() {
                    return mids.size();
                }
            });
        }

    }

    @Override
    public void updateResources(String rid, List<String> resources) {
        jdbcTemplate.update("DELETE FROM tb_crawl_role_resource WHERE role_id=?", rid);
        if (!CollectionUtils.isEmpty(resources)) {
            jdbcTemplate.batchUpdate("INSERT tb_crawl_role_resource (role_id,resource_id) VALUES (?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, rid);
                    ps.setString(2, resources.get(i));
                }

                @Override
                public int getBatchSize() {
                    return resources.size();
                }
            });
        }
    }

    @Override
    public boolean contains(String roleName) {
        return jdbcTemplate.queryForObject("select count(`name`) from tb_crawl_role where `name`=?", Integer.class,roleName)>0;
    }

    @Override
    public Role get(String id) {
        return jdbcTemplate.queryForObject("select * from tb_crawl_role where id=?", BeanPropertyRowMapper.newInstance(Role.class),id);
    }

    @Override
    public List<Role> list() {
        return jdbcTemplate.query("select * from tb_crawl_role",BeanPropertyRowMapper.newInstance(Role.class));
    }

    @Override
    public void remove(String id) {
        jdbcTemplate.update("DELETE FROM tb_crawl_role_menu WHERE role_id=?", id);
        jdbcTemplate.update("DELETE FROM tb_crawl_role_resource WHERE role_id=?", id);
        jdbcTemplate.update("DELETE FROM tb_crawl_role WHERE id=?",id);
    }

    @Override
    public void removeRoleMenuByMenuId(String menuId) {
        jdbcTemplate.update("DELETE FROM tb_crawl_role_menu WHERE menu_id=?", menuId);
    }

    @Override
    public void removeRoleResourceByResourceId(String resourceId) {
        jdbcTemplate.update("DELETE FROM tb_crawl_role_resource WHERE resource_id=?", resourceId);
    }

    public void switchStatus(String id, boolean disabled) {
        jdbcTemplate.update("update tb_crawl_role SET disabled=? WHERE id=?",disabled?1:0,id);
    }


    @Override
    public List<Role> getRoles(String userId) {
        return jdbcTemplate.query("select * from tb_crawl_role r join tb_crawl_user_role ur on r.id=ur.role_id where ur.uid=?",BeanPropertyRowMapper.newInstance(Role.class),userId);
    }
}
