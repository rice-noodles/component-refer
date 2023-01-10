package com.noodles.refer.auth.service.impl;


import com.noodles.refer.auth.constant.SecurityConstant;
import com.noodles.refer.auth.entity.User;
import com.noodles.refer.auth.service.AuthService;
import com.noodles.refer.auth.service.CurrentUserService;
import com.noodles.refer.auth.service.UserService;
import com.noodles.refer.auth.utils.JwtTokenUtils;
import com.noodles.refer.auth.vo.JwtUser;
import com.noodles.refer.auth.vo.UserVo;
import com.noodles.refer.auth.vo.request.LoginReq;
import com.noodles.refer.common.utils.RedisUtils;
import com.noodles.refer.common.utils.ReferBeanUtils;
import com.noodles.refer.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Noodles
 * @since 2022-11-22 16:35
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUserService currentUserService;

    @Override
    public ResultVo<UserVo> login(LoginReq param) {
        User user = userService.getByUsername(param.getUsername());
        // 校验密码
        if (userService.check(param.getPassword(), user.getPassword())) {
            UserVo resp = ReferBeanUtils.copyProperties(user, UserVo.class);
            resp.setToken(createToken(user));
            return ResultVo.success(resp);
        }
        return ResultVo.fail("用户名或密码错误");
    }

    @Override
    public void logout() {
        RedisUtils.delete(currentUserService.getCurrentUserId().toString());
    }

    /**
     * 根据用户生成一个token
     *
     * @param user 用户
     * @return jwtToken
     */
    private String createToken(User user) {
        JwtUser jwtUser = new JwtUser(user);
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 保存token到redis
        String token = JwtTokenUtils.createToken(user.getUsername(), user.getUserId(), authorities);
        RedisUtils.set(user.getUserId().toString(), token, SecurityConstant.EXPIRATION, TimeUnit.SECONDS);
        // 保存用户信息到SecurityContext
        String tokenValue = token.replace(SecurityConstant.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication = JwtTokenUtils.getAuthentication(tokenValue);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }


}
