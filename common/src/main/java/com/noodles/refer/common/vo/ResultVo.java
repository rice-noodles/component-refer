package com.noodles.refer.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应数据格式
 *
 * @author Noodles
 * @since 2022/11/16 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -524004876904451507L;

    private int code;

    private String message;

    private T data;

    static class Constant {
        static final int SUCCESS_CODE = 1;
        static final int FAIL_CODE = 0;
        static final int UNAUTHORIZED = -1;
        static final String SUCCESS_MESSAGE = "成功";
        static final String FAIL_MESSAGE = "失败";
        static final String UNAUTHORIZED_MESSAGE = "账号或密码错误";
        static final String DENiED_MESSAGE = "没有权限访问";
    }

    public static <T> ResultVo<T> success(String message, T data) {
        return new ResultVo<>(Constant.SUCCESS_CODE, message, data);
    }

    public static <T> ResultVo<T> success(T data) {
        return success(Constant.SUCCESS_MESSAGE, data);
    }

    public static <T> ResultVo<T> success() {
        return success(null);
    }

    public static <T> ResultVo<T> fail(String message, T data) {
        return new ResultVo<>(Constant.FAIL_CODE, message, data);
    }

    public static <T> ResultVo<T> fail(T data) {
        return fail(Constant.FAIL_MESSAGE, data);
    }

    public static <T> ResultVo<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> ResultVo<T> fail() {
        return fail(Constant.FAIL_MESSAGE, null);
    }

    public static <T> ResultVo<T> unauthorized() {
        return new ResultVo<>(Constant.UNAUTHORIZED, Constant.UNAUTHORIZED_MESSAGE, null);
    }

    public static <T> ResultVo<T> denied() {
        return new ResultVo<>(Constant.UNAUTHORIZED, Constant.DENiED_MESSAGE, null);
    }

}
