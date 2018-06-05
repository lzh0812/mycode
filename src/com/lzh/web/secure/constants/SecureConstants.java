package com.lzh.web.secure.constants;

public class SecureConstants {

    /** 加密类型：DES对称加密 */
    public static final String ENCRYPT_TYPE_DES = "1";

    /** 加密类型：XOR异或加密 */
    public static final String ENCRYPT_TYPE_XOR = "2";

    /** 默认DES对称加密算法的通用秘钥 */
    public static final String SECRET_KEY_DES = "osvuwzfk";

    /** 默认XOR异或加密算法的通用秘钥 */
    public static final String SECRET_KEY_XOR = "a90s9d0asoqpnfie4";

    /** 当前会话中的token信息 */
    public static final String SESSION_TOKEN = "session.token";

    /** 参数中存储token的属性名 */
    public static final String TOKEN_ARG_NAME = "x-csrf-token";

}
