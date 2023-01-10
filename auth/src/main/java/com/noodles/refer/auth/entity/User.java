package com.noodles.refer.auth.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Noodles
 * @since 2023/1/9 17:12
 */
@Builder
@Data
public class User {

    private Long userId;

    private String username;

    private String password;

    List<Role> roles;

}
