package com.noodles.refer.auth.service;

import com.noodles.refer.auth.entity.User;

/**
 * @author Noodles
 * @since 2023/1/9 17:32
 */
public interface UserService {

    User getByUsername(String username);

    /**
     * 密码校验
     *
     * @param curPassword 当前密码
     * @param password    真实密码
     * @return 校验结果
     */
    boolean check(String curPassword, String password);

}
