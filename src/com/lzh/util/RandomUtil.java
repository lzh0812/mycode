package com.lzh.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static void main(String[] args) {
        // for(int i = 0; i<100; i++){
        // System.out.println(RandomUtil.RandomUtil());
        // }
        System.out.println(RandomUtil.randomChecknum(6));
    }

    /**
     * 获取uuid，小写
     * 
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取uuid，大写
     * 
     * @return
     */
    public static String randomUUIDUpper() {
        return randomUUID().toUpperCase();
    }

    /**
     * 随机数产生器
     * 
     * @return
     */
    public static Random getRandom() {
        return new Random(randomUUID().hashCode());
    }

    /**
     * 获取一个随机的整形数
     * 
     * @return
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * @param n 范围[0...n]
     * @return
     */
    public static int randomInt(int n) {
        return getRandom().nextInt(n);
    }

    /**
     * 获取一个随机的浮点型
     * 
     * @return
     */
    public static float randomFloat() {
        return getRandom().nextFloat();
    }

    /**
     * 获取一个随机的双精度浮点行
     * 
     * @return
     */
    public static double randomDouble() {
        return getRandom().nextDouble();
    }

    /**
     * 获取一个随机的长整形
     * 
     * @return
     */
    public static long randomLong() {
        return getRandom().nextLong();
    }

    /**
     * 获取一个随机的布尔类型
     * 
     * @return
     */
    public static boolean randomBoolean() {
        return getRandom().nextBoolean();
    }

    
    /**
     * 随机生成n位整数
     * 
     * @param n 整数位数
     * @return
     */
    public static String randomChecknum(int n) {
        String sourcenum = "0123456789";
        // 定义获取随机数的源字符串
        String siglenum = "";
        // 保存获取到的单个随机数
        String checknum = "";
        // 获取到的随机数
        int index = 0; // 获取随机数的位置
        int i = 0;
        while (i < n) {
            index = ((int) (Math.random() * 100)) % (sourcenum.length() - 1);
            // 随机生成获取随机数的位置
            siglenum = sourcenum.substring(index, index + 1);
            // 获取单个随机数
            checknum += siglenum;
            // 连接获取到的随机数
            i++;
        }
        return checknum;
    }

}
