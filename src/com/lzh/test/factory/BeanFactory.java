package com.lzh.test.factory;

public class BeanFactory {
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T createBean(Class clazz) throws InstantiationException, IllegalAccessException{
        return (T) clazz.newInstance() ;
    }
    
    

}
