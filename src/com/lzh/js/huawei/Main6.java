package com.lzh.js.huawei;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main6 {

    @SuppressWarnings({ "static-access", "resource" })
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] s = input.split(" ");
        String ori = s[0];
        boolean flag = true;
        if (s.length == 2) {
            flag = "true".equals(s[1]) ? true : false;
        }

        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();

        for (Character a : ori.toCharArray()) {
            Character key = a;
            if (!flag){
                key = a.toUpperCase(a);
            }
            if (map.keySet().contains(key)) {
                int v = map.get(key);
                map.put(key, v + 1);
            } else {
                map.put(key, 1);
            }
        }
        
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                if (flag) {
                    System.out.println(entry.getKey());
                } else {
                    for (Character x : ori.toCharArray()) {
                        if (x.toUpperCase(x) == entry.getKey()) {
                            System.out.println(x);
                            break;
                        }
                    }
                }
                break;
            }
        }

//        for (Character key : map.keySet()) {
//            if (map.get(key) == 1) {
//                if (flag) {
//                    System.out.println(key);
//                    break;
//                } else {
//                    for (Character x : ori.toCharArray()) {
//                        Character y = x.toUpperCase(x);
//                        if (y == key) {
//                            System.out.println(x);
//                            break;
//                        }
//                    }
//                }
//                break;
//            }
//        }
    }
}
