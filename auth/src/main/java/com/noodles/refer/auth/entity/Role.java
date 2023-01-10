package com.noodles.refer.auth.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Noodles
 * @since 2023/1/9 17:22
 */
@Builder
@Data
public class Role {

    private Long roleId;

    private String roleCode;

    private String roleName;

}
