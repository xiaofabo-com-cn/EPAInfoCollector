package cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.Menu;
import cn.com.xiaofabo.hca.epainfocollector.admin.domain.repository.MenuRepository;

import java.util.List;


/**
 * @author Jonsy
 *
 */
@Repository
public class MenuRepositoryJdbc implements MenuRepository{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void add(Menu tb_crawl_menu) {
        jdbcTemplate.update("INSERT tb_crawl_menu (id,label,path,`level`,`order`,`url`,`type`,`style`,`disabled`) VALUES (?,?,?,?,?,?,?,?,?)", tb_crawl_menu.getId(), tb_crawl_menu.getLabel(), tb_crawl_menu.getPath(), tb_crawl_menu.getLevel(), tb_crawl_menu.getOrder(), tb_crawl_menu.getUrl(), tb_crawl_menu.getType(), tb_crawl_menu.getStyle(), tb_crawl_menu.isDisabled() ? 1 : 0);
    }

    @Override
    public void update(Menu tb_crawl_menu) {
        jdbcTemplate.update("update tb_crawl_menu SET label=?,`order`=?,url=?,disabled=?,`type`=?,`style`=? WHERE id=?", tb_crawl_menu.getLabel(), tb_crawl_menu.getOrder(), tb_crawl_menu.getUrl(), tb_crawl_menu.isDisabled() ? 1 : 0, tb_crawl_menu.getType(), tb_crawl_menu.getStyle(), tb_crawl_menu.getId());
    }

    @Override
    public Menu get(String id) {
        return jdbcTemplate.queryForObject("select * from tb_crawl_menu where id=?",createMapper(),id);
    }

    @Override
    public boolean contains(String id) {
        return jdbcTemplate.queryForObject("select count(id) from tb_crawl_menu where id=?",Integer.class,id)>0;
    }

    @Override
    public List<Menu> list() {
        return jdbcTemplate.query("select * from tb_crawl_menu",createMapper());
    }

    @Override
    public void remove(String id) {       //删除父节点没必要删除所有子节点，因为删除父节点后子节点并不会被展示
        jdbcTemplate.update("DELETE FROM tb_crawl_menu WHERE id=?",id);
    }

    public void switchStatus(String id,boolean disabled){ //禁用父节点没必要禁用所有子节点，同上
        jdbcTemplate.update("update tb_crawl_menu SET disabled=? WHERE id=?",disabled?1:0,id);
    }

    private RowMapper<Menu> createMapper() {
//        return (rs, rowNum) -> {
//            Menu tb_crawl_menu = new Menu();
//            tb_crawl_menu.setId(rs.getString("id"));
//            tb_crawl_menu.setLabel(rs.getString("label"));
//            tb_crawl_menu.setUrl(rs.getString("url"));
//            tb_crawl_menu.setDisabled(rs.getBoolean("disabled"));
//            tb_crawl_menu.setPath(rs.getString("path"));
//            tb_crawl_menu.setOrder(rs.getInt("order"));
//            tb_crawl_menu.setType(rs.getInt("type"));
//            tb_crawl_menu.setStyle(rs.getString("style"));
//            return tb_crawl_menu;
//        };
        return BeanPropertyRowMapper.newInstance(Menu.class);
    }

    @Override
    public List<Menu> roleMenus(String roleId) {
        return jdbcTemplate.query("select m.* from tb_crawl_menu m join tb_crawl_role_menu rm on m.id=rm.menu_id where rm.role_id=?", createMapper(), roleId);
    }

    @Override
    public List<Menu> getNavMenus(String userId) {
        return jdbcTemplate.query("select m.* from tb_crawl_menu m join tb_crawl_role_menu rm on m.id=rm.menu_id join tb_crawl_user_role ur on rm.role_id=ur.role_id where m.disabled=0 and ur.uid=?", createMapper(),userId);
    }
}
