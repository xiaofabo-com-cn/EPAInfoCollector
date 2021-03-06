package cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.assembler;

import org.springframework.util.CollectionUtils;

import cn.com.xiaofabo.hca.epainfocollector.admin.domain.modle.User;
import cn.com.xiaofabo.hca.epainfocollector.admin.infrastructure.BeanUtil;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.commondobject.ProfileCommand;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.commondobject.UserCommond;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jonsy
 *
 */
public class UserAssembler {

    public static User commondToDomain(UserCommond commond) {
        User user=new User();
        BeanUtil.copeProperties(commond,user);
        return user;
    }

    public static User commondToDomain(String uid, UserCommond commond) {
        User user = new User();
        BeanUtil.copeProperties(commond, user);
        user.setId(uid);
        return user;
    }

    public static User profileToDomain(String uid, ProfileCommand commond) {
        User user = new User();
        BeanUtil.copeProperties(commond, user);
        user.setId(uid);
        return user;
    }


    public static UserDto domainToDto(User user){
        UserDto dto=new UserDto();
       BeanUtil.copeProperties(user,dto);
        return dto;
    }

    public static List<UserDto> domainToDto(List<User> user){
       if(CollectionUtils.isEmpty(user)){
           return null;
       }
        List<UserDto> dtos=new ArrayList<>(user.size());
        user.stream().forEach(user1 -> {
            dtos.add(domainToDto(user1));
        });
        return dtos;
    }

}
