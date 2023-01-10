package com.noodles.refer.auth.service.impl;

import com.noodles.refer.auth.entity.User;
import com.noodles.refer.auth.service.CurrentUserService;
import com.noodles.refer.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Noodles
 * @since 2022/11/25 9:40
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Autowired
    private UserService userService;

    @Override
    public User getCurrentUser() {
        return userService.getByUsername(getCurrentUsername());
    }

    @Override
    public Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        throw new UsernameNotFoundException("获取当前用户失败，用户未登录");
    }

}
