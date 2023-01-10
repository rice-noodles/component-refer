package com.noodles.refer.auth.vo;

import lombok.Data;
import org.apache.catalina.Role;

import java.util.List;

/**
 * @author Noodles
 * @since 2023/1/9 17:24
 */
@Data
public class UserVo {

    private Long userId;

    private String username;

    private String token;

    private List<Role> roles;

}
