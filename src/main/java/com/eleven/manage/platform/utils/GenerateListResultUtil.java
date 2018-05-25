package com.eleven.manage.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywl
 * @date 2018/5/24
 **/
public class GenerateListResultUtil<S,T> {
    private static Logger logger = LoggerFactory.getLogger(GenerateListResultUtil.class);

    public GenerateListResultUtil() {
    }
    public List<T> generate(List<S> source,Class<T> clazz){
        List<T> result =new ArrayList<>();
        if(!CollectionUtils.isEmpty(source)){
                source.stream().map(t->{
                    T target = null;
                    try{
                        target =clazz.newInstance();
                        BeanUtils.copyProperties(t,target);
                    }catch (Exception e){
                        logger.error("泛型实例化错误",e);
                    }
                    return target;
                }).collect(Collectors.toList());
        }
        return result;
    }
    public T generateOne(List<S> source,Class<T> clazz){
        List<T> result = this.generate(source,clazz);
        if(CollectionUtils.isEmpty(result)){
            return null;
        }else {
            Assert.state(result.size() == 1,"查询时出现重复数据!");
            return result.get(0);
        }
    }
}
