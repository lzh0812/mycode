package com.lzh.web.secure.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    
    public static void main(String[] args){
//        for(int i = 0; i<100; i++){
//            System.out.println(RandomUtil.RandomUtil());
//        }
        System.out.println(RandomUtil.randomUUID());
    }
    
    /**
     * 获取uuid，小写
     * @return
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 获取uuid，大写
     * @return
     */
    public static String randomUUIDUpper(){
        return randomUUID().toUpperCase();
    }
    
    /**
     * 随机数产生器
     * @return
     */
    public static Random getRandom(){
        return new Random(randomUUID().hashCode());
    }
    
    /**
     * 获取一个随机的整形数
     * @return
     */
    public static int randomInt(){
        return getRandom().nextInt();
    }
    
    
    /**
     * @param n 范围[0...n]
     * @return
     */
    public static int randomInt(int n){
        return getRandom().nextInt(n);
    }
    
    /**
     * 获取一个随机的浮点型
     * @return
     */
    public static float randomFloat(){
        return getRandom().nextFloat();
    }
    
    /**
     * 获取一个随机的双精度浮点行
     * @return
     */
    public static double randomDouble(){
         return getRandom().nextDouble();
    }
    
    /**
     * 获取一个随机的长整形
     * @return
     */
    public static long randomLong(){
        return getRandom().nextLong();
    }
    
    /**
     * 获取一个随机的布尔类型
     * @return
     */
    public static boolean randomBoolean(){
        return getRandom().nextBoolean();
    }

}
