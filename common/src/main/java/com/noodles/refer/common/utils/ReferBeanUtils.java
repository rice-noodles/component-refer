package com.noodles.refer.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 需要spring
 *
 * @author Noodles
 * @since 2023/1/9 14:22
 */
public class ReferBeanUtils {

    /**
     * 对spring的BeanUtils.copyProperties的再封装
     *
     * @param source 被复制的对象
     * @param clazz  复制的目标类
     * @param <T>    Class的对应类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("copy object failed");
    }

    /**
     * 拷贝列表
     *
     * @param sourceList 被复制的列表
     * @param clazz      复制的目标类
     * @param <T>        Class的对应类型
     * @return 目标列表
     */
    public static <T> List<T> copyListProperties(List<?> sourceList, Class<T> clazz) {
        return sourceList.stream()
                .map(o -> copyProperties(o, clazz))
                .collect(Collectors.toList());
    }

}
