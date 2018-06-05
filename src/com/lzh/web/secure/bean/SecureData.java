package com.lzh.web.secure.bean;

import java.io.Serializable;

/**
 * 封装数据的bean
 * 
 * @author Jenkin
 * @param <T>
 */
public class SecureData<T> implements Serializable {

    private static final long serialVersionUID = -9071800838328405519L;

    private T data;

    private String verifyCode;

    public SecureData() {
        super();
    }

    public SecureData(T data, String verifyCode) {
        super();
        this.data = data;
        this.verifyCode = verifyCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
