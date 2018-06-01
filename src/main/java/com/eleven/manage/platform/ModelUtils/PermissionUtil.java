package com.eleven.manage.platform.ModelUtils;

import com.eleven.manage.platform.dto.basic.PermissionMenuMapperDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限工具类
 * @author ywl
 * @date 2018/5/31
 **/
public class PermissionUtil {

    public static List<Integer> generatePerMenu(List<PermissionMenuMapperDTO> sourceList){
        List<Integer> result =new ArrayList<>();
        if(!CollectionUtils.isEmpty(sourceList)){
            result = sourceList.stream().map(t->t.getMenuId()).collect(Collectors.toList());
        }
        return result;
    }
}
