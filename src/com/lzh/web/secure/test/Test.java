package com.lzh.web.secure.test;

import java.util.HashMap;
import java.util.Map;

import com.lzh.web.secure.bean.SecureBean;
import com.lzh.web.secure.bean.SecureData;

public class Test {
    
    public static void main(String[] args){
        
        Map map = new HashMap();
        map.put("2332", "4234234");
//        SecureBean<Map> secureBean = new SecureBean<Map>(map);
        
        SecureData<String> secureData = new SecureData<String>();
        secureData.setData("12312312312");
        secureData.setVerifyCode("2342342342");
        
        SecureBean<SecureData> secureBean = new SecureBean<SecureData>(secureData);
        SecureBean<SecureData> secureBean2 = new SecureBean<SecureData>();
        secureBean2.setEncryptedText( secureBean.getEncryptedText());
        //secureBean2.setEncryptType(secureBean.getEncryptType());
        secureBean2.setVerifyCode(secureBean.getVerifyCode());
        
        SecureData data = secureBean2.getData();
        
        System.out.println(data);
        
    }

}
