package com.lzh.web.secure.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.lzh.web.secure.bean.SecureBean;
import com.lzh.web.secure.exception.SecureException;

@Component
@Aspect
public class EncryptionAspect {

    private Logger logger = Logger.getLogger(EncryptionAspect.class.toString());

    /**
     * 切点，作用于加密注解的方法
     */
    @Pointcut("@annotation(com.lzh.web.secure.annotation.Encryption)")
    public void pointcut() {

    }

    @SuppressWarnings("rawtypes")
    @Before("pointcut()")
    public void encryption(JoinPoint point) {
        Object[] args = point.getArgs();
        // 验证信息原始性
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                // 传入参数是安全类的实例
                if (args[i] != null && args[i] instanceof SecureBean) {
                    SecureBean baseSecureBean = (SecureBean) args[i];
                    if (!baseSecureBean.verifyInfoOriginal()) {
                        logger.info("######信息安全验证未通过");
                        throw new SecureException("信息安全验证未通过");
                    }
                }
            }
        }
    }
}
