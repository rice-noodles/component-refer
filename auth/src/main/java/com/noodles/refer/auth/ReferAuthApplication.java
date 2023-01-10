package com.noodles.refer.auth;

import com.noodles.refer.common.utils.RedisUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Noodles
 * @since 2023/1/9 17:10
 */
@Import(RedisUtils.class)
@SpringBootApplication
public class ReferAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReferAuthApplication.class, args);
    }

}
