package cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.assembler;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.Menu;
import cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.BeanUtil;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.commondobject.MenuCreateCommand;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.commondobject.MenuUpdateCommond;


/**
 * @author Jonsy
 *
 */
public class MenuAssembler {

    public static Menu updateCommendToDomain(String id, MenuUpdateCommond updateCommond) {
        Menu menu=new Menu();
      BeanUtil.copeProperties(updateCommond,menu);
        menu.setId(id);
        return menu;
    }

    public static Menu createCommendToDomain(MenuCreateCommand creteCommand){
        Menu menu=new Menu();
        BeanUtil.copeProperties(creteCommand,menu);
        return menu;
    }
}
