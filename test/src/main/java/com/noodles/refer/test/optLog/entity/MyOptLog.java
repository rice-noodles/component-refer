package com.noodles.refer.test.optLog.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Noodles
 * @since 2023/1/6 10:48
 */
@Data
public class MyOptLog implements Serializable {

    private static final long serialVersionUID = 4007669044647343613L;

    /*** 记录的业务所对应的唯一标识 */
    private String bizNo;

    /*** 操作员 */
    private String operator;

    /*** 操作内容 */
    private String content;

    /*** 业务状态 */
    private Integer status;

    /*** 业务是否成功执行 */
    private Boolean isSuccess;

    /*** 备注 */
    private String remark;

    /*** 创建时间 */
    private LocalDateTime createTime;

}
