package com.mac;

import com.mac.utils.ErrorUtil;
import com.mac.vo.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yog
 */
@SpringBootApplication
@ControllerAdvice
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    /**
     * 全局异常处理
     *
     * @param ex Exception
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> defaultExceptionHandler(Exception ex) {
        return ErrorUtil.handException(ex);
    }

}
