package com.cloud.common.base.excetion;

import com.cloud.common.base.result.R;
import com.cloud.common.base.result.ResultCodeEnum;

/**
 * @author xiaofang
 * @date 2022/9/3
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -7034897190745766399L;
    private final R r;

    public R getR() {
        return this.r;
    }

    public CustomException(R r) {
        super(ResultCodeEnum.FAIL.getMessage());
        this.r = R.error().code(ResultCodeEnum.FAIL.getCode()).message(ResultCodeEnum.FAIL.getMessage());
    }

    public  CustomException(String message) {
        super(message);
        this.r = R.error().message(message).code(ResultCodeEnum.FAIL.getCode());
    }

    // 用指定的详细信息和原因构造一个新的异常
    public CustomException(ResultCodeEnum resultCodeEnum, Throwable cause){
        super(cause);
        this.r = R.setResult(resultCodeEnum);
    }

}
