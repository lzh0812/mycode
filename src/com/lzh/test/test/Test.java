package com.lzh.test.test;

import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in); 
        System.out.println("请输入名："); 
        String ori = sc.nextLine(); 
        System.out.println(reverse(ori));
        
    }
    
    public static char[] reverse(String ori){
        char[] c = ori.toCharArray();
        //System.out.println(ori);
        char temp;
        int length = c.length - 1;
        for(int i = 0;i<=length/2;i++){
            temp = c[i];
            c[i] = c[length-i];
            c[length-i] = temp;
        }
        
        String dest = c.toString();
        //System.out.println(c);
        return c;
        
    }
}
