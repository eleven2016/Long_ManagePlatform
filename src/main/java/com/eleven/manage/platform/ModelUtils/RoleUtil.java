package com.eleven.manage.platform.ModelUtils;

import com.eleven.manage.platform.dto.basic.RolePermissionMapperDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywl
 * @date 2018/5/31
 **/
public class RoleUtil {
    public static List<Integer> generateRolePer(List<RolePermissionMapperDTO> sourceList){
        List<Integer> result =new ArrayList<>();
        if(!CollectionUtils.isEmpty(sourceList)){
            result = sourceList.stream().map(t->t.getPermissionId()).collect(Collectors.toList());
        }
        return result;
    }
}
