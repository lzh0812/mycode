package com.lzh.util;

import java.math.BigInteger;
import java.util.Date;

public class MathUtil {
    
    public static void main(String[] args) {
        System.out.println(parseIntOf8("12"));
    }
    
    /**
     * 将十进制转成二进制
     * @param n
     * @return
     */
    public static String toBinaryString(int n){
        String result = Integer.toBinaryString(n);
        
        return result;
    }
    
    /**
     * 将十进制转成8进制
     * @param n
     * @return
     */
    public static String toOctalString(int n){
        String result = Integer.toOctalString(n);
        
        return result;
    }
    
    /**
     * 将十进制转成16进制
     * @param n
     * @return
     */
    public static String toHexString(int n){
        String result = Integer.toHexString(n);
        //result = result.toUpperCase(); //转成大写
        
        return result;
    }
    
    
    /**
     * 将二进制转换成十进制
     * @param s 二进制的String表达式
     * @return 转换后的整数
     */
    public static BigInteger parseIntOf2(String s){
        BigInteger bi = new BigInteger(s, 2);
        //int result = Integer.parseInt(s, 2);
        return bi;
    }
    
    /**
     * 将八进制转换成十进制
     * @param s 二进制的String表达式
     * @return 转换后的整数
     */
    public static BigInteger parseIntOf8(String s){
        BigInteger bi = new BigInteger(s, 8);
        //int result = Integer.parseInt(s, 8);
        return bi;
    }
    
    /**
     * 将16进制转换成十进制
     * @param s 二进制的String表达式
     * @return 转换后的整数
     */
    public static BigInteger parseIntOf16(String s){
        BigInteger bi = new BigInteger(s, 16);
        //int result = Integer.parseInt(s, 16);
        return bi;
    }
    
    
    
    /**
     * 判断两个元素的值是否一致
     * @param ori
     * @param dest
     * @return
     */
    public final static boolean isEquals(Object ori, Object dest){
        boolean isEuqals = false;
        
        if(ori == null && dest == null){
            return true;
        }
        
        if((ori == null && dest !=null ) || (ori != null && dest == null)){
            return false;
        }
        
        // 先判断下，两个对象是否相等
        if(ori.equals(dest)){
            isEuqals = true;
        }
        
        // 如果是一些封装类型，则按类型判断
        if(ori instanceof String && dest instanceof String && ori.equals(dest)){
            isEuqals = true;
        } else if(ori instanceof Character && dest instanceof Character && ori.equals(dest)){
            isEuqals = true;
        } else if(ori instanceof Byte && dest instanceof Byte && ori.equals(dest)){
            isEuqals = true;
        } else if(ori instanceof Short && dest instanceof Short && ((Short)ori).shortValue() == ((Short)dest).shortValue()){
            isEuqals = true;
        } else if(ori instanceof Integer && dest instanceof Integer && ori.equals(dest)){
            isEuqals = true;
        } else if(ori instanceof Long && dest instanceof Long && ori.equals(dest)){
            isEuqals = true;
        } else if(ori instanceof Double && dest instanceof Double && ((Double)ori).doubleValue() == ((Double)dest).doubleValue()){
            isEuqals = true;
        } else if(ori instanceof Float && dest instanceof Float && ((Float)ori).floatValue() == ((Float)dest).floatValue()){
            isEuqals = true;
        } else if(ori instanceof Date && dest instanceof Date && ((Date)ori).getTime() == ((Date)dest).getTime()){
            isEuqals = true;
        } else if(ori == dest){
            //如果属于一些基本类型如 int,double,long等，则直接比较是否相等
            isEuqals = true;
        }
        return isEuqals;
    }

}
