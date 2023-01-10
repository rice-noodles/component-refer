package com.noodles.refer.optLog.service;

import com.noodles.refer.optLog.entity.OptLogOps;

import java.util.List;

/**
 * @author Noodles
 * @since 2023/1/5 17:04
 */
public interface OptLogService<T> {

    void save(OptLogOps optLogOps);

    T query(T t);

    List<T> list(T t);

}
