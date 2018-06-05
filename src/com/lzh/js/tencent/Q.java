package com.lzh.js.tencent;

import java.util.Scanner;

/**
 *小Q的父母要出差N天，走之前给小Q留下了M块巧克力。小Q决定每天吃的巧克力数量不少于前一天吃的一半，但是他又不想在父母回来之前的某一天没有巧克力吃，
 *请问他第一天最多能吃多少块巧克力
 * @author Jenkin
 *
 */
public class Q {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        float x = 1.0f;
        for(int i=0;i<N;i++){
            x = (float) (x * 0.5);
        }
        
        System.out.println(Math.round(M/(2*(1-x))));
    }
}
