package cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.User;
import cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository.UserRepository;


/**
 * @author Jonsy
 *
 */
@Repository
public class UserRepositoryJdbc implements UserRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(User tb_crawl_user) {
        jdbcTemplate.update("INSERT tb_crawl_user (id,username,password,email,disabled,createTime,salt) VALUES (?,?,?,?,?,?,?)",tb_crawl_user.getId(),tb_crawl_user.getUsername(),tb_crawl_user.getPassword(),tb_crawl_user.getEmail(),tb_crawl_user.isDisabled()?1:0,new Date(),tb_crawl_user.getSalt());
    }

    @Override
    public void update(User tb_crawl_user) {
        jdbcTemplate.update("UPDATE tb_crawl_user SET username=?,email=?,password=? WHERE id=?",tb_crawl_user.getUsername(),tb_crawl_user.getEmail(),tb_crawl_user.getPassword(),tb_crawl_user.getId());
    }

    @Override
    public void updateRoles(String uid, List<String> rids) {
        jdbcTemplate.update("DELETE FROM tb_crawl_user_role WHERE uid=?", uid);
        if (!CollectionUtils.isEmpty(rids)) {
            jdbcTemplate.batchUpdate("INSERT tb_crawl_user_role (uid,role_id) VALUES (?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, uid);
                    ps.setString(2, rids.get(i));
                }

                @Override
                public int getBatchSize() {
                    return rids.size();
                }
            });
        }
    }

    @Override
    public User get(String id) {
        return jdbcTemplate.queryForObject("select * from tb_crawl_user where id=?", BeanPropertyRowMapper.newInstance(User.class),id);
    }

    @Override
    public boolean contains(String name) {
        return jdbcTemplate.query("select count(username) from tb_crawl_user where username=?", rs -> rs.getInt(1)>0,name);
    }

    @Override
    public List<User> list() {
        return jdbcTemplate.query("select * from tb_crawl_user where username <> 'root'", BeanPropertyRowMapper.newInstance(User.class));
    }


    @Override
    public boolean hasResourcePermission(String uid,String resourceCode) {
        return jdbcTemplate.query("select count(*) from tb_crawl_user_role ur join role_resource rr on ur.role_id=rr.role_id where ur.uid=? and rr.resource_id=?",rs -> rs.getInt(0)>0,uid,resourceCode);
    }



    @Override
    public void remove(String id) {
        User tb_crawl_user=get(id);
        if(tb_crawl_user.isRoot()){
            return;
        }
        jdbcTemplate.update("DELETE FROM tb_crawl_user WHERE id=?",id);
        jdbcTemplate.update("DELETE FROM tb_crawl_user_role WHERE uid=?",id);
    }

    public void switchStatus(String id,boolean disabled){
        jdbcTemplate.update("update tb_crawl_user SET disabled=? WHERE id=?",disabled?1:0,id);
    }


    @Override
    public User findByUserName(String username) {
        try {
            return jdbcTemplate.queryForObject("select * from tb_crawl_user where username=? ", BeanPropertyRowMapper.newInstance(User.class), username);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<User> getUserByUname(String username) {
        return jdbcTemplate.query("select * from tb_crawl_user where username =?",BeanPropertyRowMapper.newInstance(User.class),username);
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return jdbcTemplate.query("select * from tb_crawl_user  where email =?",BeanPropertyRowMapper.newInstance(User.class),email);
    }

}
