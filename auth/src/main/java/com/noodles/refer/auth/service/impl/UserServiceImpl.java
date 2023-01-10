package com.noodles.refer.auth.service.impl;

import com.noodles.refer.auth.entity.Role;
import com.noodles.refer.auth.entity.User;
import com.noodles.refer.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noodles
 * @since 2023/1/10 9:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getByUsername(String username) {
        Role role = Role.builder()
                .roleId(123456L)
                .roleCode("admin")
                .roleName("管理员")
                .build();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return User.builder()
                .userId(123456L)
                .username("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .roles(roles)
                .build();
    }

    @Override
    public boolean check(String curPassword, String password) {
        return bCryptPasswordEncoder.matches(curPassword, password);
    }

}
