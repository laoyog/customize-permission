package com.mac.utils;

import com.mac.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;


/**
 * @author yog
 */
@Slf4j
public class ErrorUtil {

    public static Result<String> handException(Exception ex) {
        try {
            log.error("异常 error:{}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        } catch (MethodArgumentNotValidException e) {
            return getParamErrorMessage(e);
        } catch (HttpRequestMethodNotSupportedException e) {
            return new Result<>("40001","请求方式错误");
        } catch (IllegalArgumentException e) {
            return new Result<>("40002", e.getMessage());
        } catch (RuntimeException e) {
            return new Result<>("50000", e.getMessage());
        } catch (BindException e) {
            return new Result<>("40010", e.getAllErrors().get(0).getDefaultMessage());
        } catch (Exception e) {
            return new Result<>("40000","系统异常");
        }
    }

    private static Result<String> getParamErrorMessage(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder();
        //按需重新封装需要返回的错误信息
        //解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(";  ");
            }
            stringBuilder.append(error.getDefaultMessage());
        }
        return new Result<>("400","异常 error:" + stringBuilder.toString());
    }
}