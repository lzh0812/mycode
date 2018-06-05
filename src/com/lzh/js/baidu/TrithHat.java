package com.lzh.js.baidu;

import java.util.Scanner;

/**
 * 第三小的帽子
 * 输入N
 * N个数
 * 计算第三小的数，如果没有，则输出-1
 * @author Jenkin
 *
 */
public class TrithHat {
    
    public static void main(String[] args) {
        int[] price = new int[1001];
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt();
        int i = 0;
        while(true){
            int k = sc.nextInt();
            if(k<=1000){
                price[k] = 1;
                i++;
            }
            if(i == n - 1){
                break;
            }
        }
        
        int x=0,y=0;
        for(int j = 0;j<price.length; j++){
            if(y==3){
                break;
            }
            if(price[j] == 1){
                x = j;
                y++;
            }
        }
        
        if(y == 3){
            System.out.println(x);
        } else{
            System.out.println(-1);
        }
    }
}
