package com.noodles.refer.test.auth;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Noodles
 * @since 2023/1/10 10:21
 */
public class AuthTest {

    @Test
    public void test() {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "admin");
        param.put("password", "admin");

        HttpResponse response = HttpRequest.post("http://127.0.0.1:8080/auth/login")
                .body(JSONUtil.toJsonStr(param))
                .execute();
        System.out.println("=================================");
        System.out.println(response.body());
        System.out.println("=================================");
    }

}
