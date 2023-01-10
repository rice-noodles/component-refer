package com.noodles.refer.auth.service;

import com.noodles.refer.auth.vo.UserVo;
import com.noodles.refer.auth.vo.request.LoginReq;
import com.noodles.refer.common.vo.ResultVo;

/**
 * @author Noodles
 * @since 2022-11-22 16:34
 */
public interface AuthService {

    ResultVo<UserVo> login(LoginReq req);

    void logout();

}
