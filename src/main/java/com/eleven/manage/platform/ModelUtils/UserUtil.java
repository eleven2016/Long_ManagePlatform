package com.eleven.manage.platform.ModelUtils;

import com.eleven.manage.platform.dto.basic.UserRoleMapperDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywl
 * @date 2018/5/31
 **/
public class UserUtil {
    public static List<Integer> generateUserRole(List<UserRoleMapperDTO> sourceList){
        List<Integer> result =new ArrayList<>();
        if(!CollectionUtils.isEmpty(sourceList)){
            result = sourceList.stream().map(t->t.getRoleId()).collect(Collectors.toList());
        }
        return result;
    }
}
