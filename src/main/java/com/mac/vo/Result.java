package com.mac.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author yog
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> {

    private String code;

    private String msg;

    private T t;

    public Result() {}

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.t = t;
    }
}
