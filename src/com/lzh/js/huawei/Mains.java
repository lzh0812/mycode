package com.lzh.js.huawei;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）
 * @author Jenkin
 *
 */
public class Mains {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            s = s.replace("0x", "").replace("x", "");
            System.out.println(parseIntOf16(s));
        }
    }
    
    public static BigInteger parseIntOf16(String s){
        BigInteger bi = new BigInteger(s, 16);
        //int result = Integer.parseInt(s, 16);
        return bi;
    }

}
