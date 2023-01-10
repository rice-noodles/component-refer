package com.noodles.refer.optLog.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Noodles
 * @since 2023/1/5 14:33
 */
@Builder
@Data
public class OptLogOps {

    /*** 记录的业务所对应的唯一标识 */
    private String bizNo;

    /*** 操作员 */
    private String operator;

    /*** 操作内容 */
    private String content;

    /*** 业务是否成功执行 */
    private Boolean isSuccess;

    /*** 备注 */
    private String remark;

    /*** 创建时间 */
    private LocalDateTime createTime;

}
