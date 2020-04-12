package com.myteam.classroomsystem.scas.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功的ResultVO格式
     **/
    public static <T> ResultVO getSuccess(String msg) {
        return new ResultVO(200, msg);
    }

    /**
     * 成功的带Data的ResultVO格式
     **/
    public static <T> ResultVO getSuccessWithData(String msg, T data) {
        return new ResultVO(200, msg, data);
    }

    /**
     * 失败的ResultVO格式
     **/
    public static <T> ResultVO getFailed(String msg) {
        return new ResultVO(100, msg);
    }

    /**
     * 用户未登陆的ResultVO格式
     */
    public static <T> ResultVO getNotAuth() {
        return new ResultVO(103, "用户未登陆");
    }

    /**
     * 自动义的ResultVO格式C
     */
    public static <T> ResultVO getcustomizeResultVO(Integer code, String msg) {
        return new ResultVO(code, msg);
    }
}
