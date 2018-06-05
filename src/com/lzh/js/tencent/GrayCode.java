package com.lzh.js.tencent;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/50959b5325c94079a391538c04267e15
        来源：牛客网

            在一组数的编码中，若任意两个相邻的代码只有一位二进制数不同， 则称这种编码为格雷码(Gray Code)，请编写一个函数，使用递归的方法生成N位的格雷码。
            给定一个整数n，请返回n位的格雷码，顺序为从0开始。
        测试样例：
    1
返回：["0","1"]
 * @author Jenkin
 *
 */
public class GrayCode {
    public String[] getGray(int n) {
        String[] array;
        if(n==1){
            array = new String[]{"0","1"};
            return array;
        }
        array = getGray(n - 1);
        String[] dest = new String[array.length * 2];
        for(int i = 0; i < array.length; i++){
            dest[i] = "0" + array[i];
            dest[array.length + i] = "1" + array[array.length - 1 - i];
        }
        return dest;
    }
}