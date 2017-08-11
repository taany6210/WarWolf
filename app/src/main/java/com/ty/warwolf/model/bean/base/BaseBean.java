package com.ty.warwolf.model.bean.base;

import java.io.Serializable;

/**
 * @ 文件名:   BaseBean
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午4:23
 * @ 描述:    bean基类
 */

public class BaseBean implements Serializable {

    /**
     * 0 成功
     */
    private int error_code;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
