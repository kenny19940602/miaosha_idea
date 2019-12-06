package com.imooc.kenny.exception;

import com.imooc.kenny.result.CodeMsg;
import com.imooc.kenny.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: GlobalExceptionHandler
 * Function:  异常处理器
 * Date:      2019/11/26 9:33
 * @author     Kenny
 * version    V1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({GlobalException.class,BindException.class})
    public Result<Void> exceptionHandler(Exception e) {
        e.printStackTrace();

        if (e instanceof GlobalException) {
            return Result.error(((GlobalException) e).getCodeMsg());
        } else if (e instanceof BindException) {
            List<ObjectError> errors = ((BindException) e).getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
