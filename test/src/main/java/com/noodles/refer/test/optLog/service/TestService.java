package com.noodles.refer.test.optLog.service;

import com.noodles.refer.optLog.annotation.OptLog;
import org.springframework.stereotype.Service;

/**
 * @author Noodles
 * @since 2023/1/9 13:34
 */
@Service
public class TestService {
    @OptLog(content = "【测试】操作日志注解，参数：#{#bizNo}", bizNo = "#{#bizNo}")
    public void optLogTest(String bizNo) {
        System.out.println("=================================");
        System.out.println("【测试】接收到方法参数: " + bizNo);
        System.out.println("=================================");
    }
}
