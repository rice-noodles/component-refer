package com.noodles.refer.auth.controller;

import com.noodles.refer.auth.service.AuthService;
import com.noodles.refer.auth.vo.UserVo;
import com.noodles.refer.auth.vo.request.LoginReq;
import com.noodles.refer.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Noodles
 * @since 2023/1/10 10:16
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResultVo<UserVo> login(@RequestBody LoginReq req) {
        return authService.login(req);
    }

    @PostMapping(value = "/logout")
    public ResultVo<Object> logout() {
        authService.logout();
        return ResultVo.success();
    }

}
