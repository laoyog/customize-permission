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

}
