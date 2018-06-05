package com.lzh.js.tencent;

import java.util.*;

/**
 * 春节期间小明使用微信收到很多个红包，非常开心。在查看领取红包记录时发现，某个红包金额出现的次数超过了红包总数的一半。请帮小明找到该红包金额。
 * 写出具体算法思路和代码实现，要求算法尽可能高效。给定一个红包的金额数组gifts及它的大小n，请返回所求红包的金额。若没有金额超过总数的一半，返回0。
 * @author Jenkin
 *
 */
public class Gift {
    public int getValue(int[] gifts, int n) {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<n;i++){
            if(map.keySet().contains(gifts[i])){
                map.put(gifts[i], map.get(gifts[i])+1);
            } else{
                map.put(gifts[i], 1);
            }
        }
        int max = 1;
        int gift = 0;
        for(int key : map.keySet()){
            if(map.get(key)>max){
                max = map.get(key);
                gift = key;
            }
        }
        
        if(max > (n/2)){
            return gift;
        }else{
            return 0;
        }
    }
    
//    public static void main(String[] args) {
//        int[] arr = {1,2,3,3};
//        int n = 4;
//        System.out.println(getValue(arr, 4));
//    }
}