package com.imooc.kenny.exception;

import com.imooc.kenny.result.CodeMsg;

/**
 * ClassName: GlobalException
 * Function:  本地异常
 * Date:      2019/11/26 9:26
 * @author     Kenny
 * version    V1.0
 */
public class GlobalException extends RuntimeException{

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}
