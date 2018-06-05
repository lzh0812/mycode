package com.lzh.js.tencent;



/**
小Q定义了一种数列称为翻转数列:
给定整数n和m, 满足n能被2m整除。对于一串连续递增整数数列1, 2, 3, 4..., 每隔m个符号翻转一次, 最初符号为'-';。
例如n = 8, m = 2, 数列就是: -1, -2, +3, +4, -5, -6, +7, +8.
而n = 4, m = 1, 数列就是: -1, +2, -3, + 4.
小Q现在希望你能帮他算算前n项和为多少。 
输入描述:
输入包括两个整数n和m(2 <= n <= 109, 1 <= m), 并且满足n能被2m整除。


输出描述:
输出一个整数, 表示前n项和。

输入例子1:
8 2

输出例子1:
8

 * @author Jenkin
 *
 */
import java.util.Scanner;
public class RevesAarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long m = sc.nextInt();
        
        long sum = 0;
        long flag = 0;
        for(long i = 0; i<n; i++){
            long temp = 0;
            
            temp = i+1;
            
            if(flag == 0){
                temp = 0 - temp;
            }
            
            if((i+1) % m == 0){
                if(((i+1)/m)%2 == 1 && flag == 0){
                    flag = 1;
                }else if(((i+1)/m)%2 == 0 && flag == 1){
                    flag = 0;
                }
            }
            
            sum += temp;
        }
        System.out.println(sum); 
    }

}
