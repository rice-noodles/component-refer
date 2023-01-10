package com.noodles.refer.auth.service;

import com.noodles.refer.auth.entity.User;

/**
 * @author Noodles
 * @since 2022/11/24 15:40
 */
public interface CurrentUserService {

    User getCurrentUser();

    Long getCurrentUserId();

    String getCurrentUsername();

}
