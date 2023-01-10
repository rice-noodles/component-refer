package com.noodles.refer.test.optLog;

import com.noodles.refer.test.optLog.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Noodles
 * @since 2023/1/6 10:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OptLogTest {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        testService.optLogTest("123456");
    }

}
