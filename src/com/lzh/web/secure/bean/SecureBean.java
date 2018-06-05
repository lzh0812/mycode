package com.lzh.web.secure.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lzh.web.secure.constants.SecureConstants;
import com.lzh.web.secure.util.DesEncryptUtil;
import com.lzh.web.secure.util.RandomUtil;
import com.lzh.web.secure.util.XORCryptoUtil;

public class SecureBean<T> implements Serializable {

    private Logger logger = Logger.getLogger(SecureBean.class);

    private static final long serialVersionUID = 1244380193809402754L;

    /**
     * 元素数据对象
     */
    private SecureData<T> secureData;

    /**
     * 加密方式
     */
    @JsonProperty("encryptType")
    private String encryptType;

    /**
     * 加密原始数据密文
     */
    @JsonProperty("encryptedText")
    private String encryptedText;

    @JsonProperty("verifyCode")
    private String verifyCode;

    /**
     * 构造函数
     */
    public SecureBean() {
        super();
    }

    /**
     * 构造函数
     */
    public SecureBean(T secureData) {
        this._constructor(secureData, null);
    }

    /**
     * 构造函数
     */
    public SecureBean(T secureData, String encryptType) {
        this._constructor(secureData, encryptType);
    }

    /**
     * 构造函数通用逻辑
     * 
     * @param data
     * @param encryptType
     */
    private void _constructor(T data, String encryptType) {
        String uuid = RandomUtil.randomUUID();
        SecureData<T> secureData = new SecureData<T>(data, uuid);
        this.secureData = secureData;
        this.verifyCode = uuid;
        this.encryptedText = this.encryptObj(secureData);
        this.encryptType = StringUtils.isBlank(encryptType) ? SecureConstants.ENCRYPT_TYPE_DES : encryptType;
    }

    /**
     * 获取传入的原生对象
     *
     * @return
     */
    @JsonIgnore
    @XmlTransient
    public T getData() {
        SecureData<T> secureData = this.getSecureData();
        return secureData != null ? secureData.getData() : null;
    }

    @SuppressWarnings("unchecked")
    @JsonIgnore
    @XmlTransient    
    private SecureData<T> getSecureData() {
        try {
            if (this.secureData != null) {
                return this.secureData;
            } else {
                SecureData<T> secureData = (SecureData<T>) decryptObj();
                this.secureData = secureData;
                return secureData;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public void setSecureData(SecureData<T> secureData) {
        this.secureData = secureData;
    }

    @XmlElement(name = "ENCRYPTTYPE")
    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    @XmlElement(name = "ENCRYPTEDTEXT")
    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    @XmlElement(name = "VERIFYCODE")
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 验证是否为原始信息
     * 
     * @return true：是 ；false：否
     */
    public boolean verifyInfoOriginal() {
        // 用于验证的信息为空，认为信息有问题
        if (StringUtils.isBlank(getVerifyCode())) {
            return false;
        }
        SecureData<T> secureData = getSecureData();
        // 验证对象为空，认为信息有问题
        if (secureData == null) {
            return false;
        }
        return getVerifyCode().equals(secureData.getVerifyCode());
    }

    /**
     * 序列化对象并加密，需要保证创建的bo实现序列化接口并定义唯一的序列号，否则无法反序列化
     * 
     * @param obj
     * @return
     */
    protected String encryptObj(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return this.encryptBytes(baos.toByteArray());
        } catch (IOException e) {
            logger.warn("######Java对象转字节数组出错");
            return null;
        }
    }

    /**
     * 解密并反序列化对象，需要保证创建的bo实现序列化接口并定义唯一的序列号
     * 
     * @return
     */
    protected Object decryptObj() {
        if (StringUtils.isBlank(this.encryptedText)) {
            return null;
        }
        // 用于加密的字节数组
        try {
            byte[] bytes = this.decryptString(this.encryptedText);
            // 从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            return oi.readObject();
        } catch (Exception e) {
            logger.warn("######字节数组转Java对象出错");
            return null;
        }
    }

    /**
     * 加密字节数组
     * 
     * @param bytes
     * @return
     */
    protected String encryptBytes(byte[] bytes) {
        bytes = Base64.encodeBase64(bytes);

        if (SecureConstants.ENCRYPT_TYPE_XOR.equals(this.encryptType)) {
            return XORCryptoUtil.encrypt(new String(bytes));
        } else {
            DesEncryptUtil desEncryptUtil = new DesEncryptUtil();
            desEncryptUtil.getKey(SecureConstants.SECRET_KEY_DES);
            return desEncryptUtil.getEncString(new String(bytes));
        }
    }

    /**
     * 解密字符串
     * 
     * @param str
     * @return
     */
    protected byte[] decryptString(String str) {
        if (SecureConstants.ENCRYPT_TYPE_XOR.equals(this.encryptType)) {
            return Base64.decodeBase64(XORCryptoUtil.decrypt(str));
        } else {
            DesEncryptUtil desEncryptUtil = new DesEncryptUtil();
            desEncryptUtil.getKey(SecureConstants.SECRET_KEY_DES);
            return Base64.decodeBase64(desEncryptUtil.getDesString(str).getBytes());
        }
    }
}
