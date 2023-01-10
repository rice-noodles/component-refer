package com.noodles.refer.test.optLog.service;

import com.noodles.refer.optLog.entity.OptLogOps;
import com.noodles.refer.optLog.service.OptLogService;
import com.noodles.refer.test.optLog.entity.MyOptLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Noodles
 * @since 2023/1/6 10:46
 */
@Service
public class OptLogServiceImpl implements OptLogService<MyOptLog> {

    @Override
    public void save(OptLogOps optLogOps) {
        // 保存前可以进行一些扩展操作；如设置当前用户为operator、补充备注等
        System.out.println("=================================");
        System.out.println(optLogOps.toString());
        System.out.println("=================================");
    }

    @Override
    public MyOptLog query(MyOptLog myOptLog) {
        return null;
    }

    @Override
    public List<MyOptLog> list(MyOptLog myOptLog) {
        return null;
    }
}
