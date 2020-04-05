package com.myteam.classroomsystem.scas.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    //自定义返回数据格式
    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功并返回自定义提示
    public ResultVO(String msg) {
        this.code = 100;
        this.msg = msg;
    }

    //失败并返回自定义提示
    public ResultVO(int code, String msg) {
        this.code = 200;
        this.msg = msg;
    }

    public ResultVO(String msg, T data) {
        this.code = 100;
        this.msg = msg;
        this.data = data;
    }


    //成功操作，返回成功操作提示
    public static ResultVO getSuccessResult() {
        return new ResultVO<>(100, "操作成功");
    }

    //成功操作，返回成功操作自定义提示
    public static ResultVO getSuccessResult(String msg) {
        return new ResultVO<>(100, msg);
    }

    //成功操作，返回成功操作提示以及数据
    public static <T> ResultVO getSuccessResultWithData(String msg, T data) {
        return new ResultVO<>(100, msg, data);
    }

    //成功操作，返回成功操作提示以及数据
    public static <T> ResultVO getSuccessResult(String msg, T data) {
        return new ResultVO<>(100, msg, data);
    }

    //操作失败，返回失败提示
    public static ResultVO getFailedResult() {
        return new ResultVO<>(200, "操作失败");
    }

    //操作失败，返回失败自定义提示
    public static ResultVO getFailedResult(String msg) {
        return new ResultVO<>(200, msg);
    }

    //操作失败，返回失败自定义提示
    public static ResultVO getFailedResult(int code, String msg) {
        return new ResultVO<>(code, msg);
    }


}
