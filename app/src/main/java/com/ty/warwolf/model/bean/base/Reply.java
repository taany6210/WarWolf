package com.ty.warwolf.model.bean.base;

import java.io.Serializable;

/**
 * @ 文件名:   Reply
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:00
 * @ 描述:
 */

public class Reply<T> implements Serializable{
    /**
     * 0 成功
     */
    private int error_code;
    /**
     * 封装需要返回的数据
     */
    public T result;
    /**
     * 给用户的提示信息
     */
    private String reason;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "error_code=" + error_code +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                '}';
    }
}
