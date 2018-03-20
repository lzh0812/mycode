package com.lzh.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.lzh.util.RandomUtil;

/**
 * 抽奖小程序
 * @author Jenkin
 *
 */
public class LotteryDraw {
    
    /**
     * @param num 抽奖范围
     * @param count 抽奖个数
     */
    public static Set<Integer> lotteryDrawSet(int num, int count){
        
        Set<Integer> lottery = new HashSet<Integer>();
        
        do{
            lottery.add(RandomUtil.randomInt(num));
        } while(lottery.size() < count);
        
        return lottery;
    }
    
    
    /**
     * @param num 抽奖范围 1-num
     * @param count 获奖人数 count个
     * @param x 设立获奖等级和每个等级对应的人数，必须两个为一组，如 1,10,2,20表示 一等奖10名，二等奖20名
     */
    public static void lotteryDraw(int num, int count, int... x){
        if(num < count){
            System.out.println("获奖人数大于抽奖范围，请检查！");
            return;
        }
        Set<Integer> lottery = lotteryDrawSet(num, count);
        if(x.length == 0){
            print(lottery);
        } else if(x.length%2 != 0){ //参数个数必须是2的倍数
            System.out.println("输入参数个数不对！");
        } else if(x.length > 2){
            
            int length = x.length;
            int i;
            int total = 0;
            for(i = 0; i<length; i+=2){
                total += x[i+1];
            }
            
            if(num < total){
                System.out.println("获奖总人数大于抽奖范围，请检查！");
                return;
            }
            
            if(total != count){
                System.out.println("\n*****注意：指定的获奖人数:"+count+"人与各等级获奖人数总数:"+total+"人不一致，已按照各等级获奖总数之和进行获奖名单确定！*****\n");
                count = total;
                lottery = lotteryDrawSet(num, count);
            }
            
            Map<Integer, Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
            Set<Integer> levelSet = new HashSet<Integer>();
            for(i = 0; i<length; i+=2){
                int level = x[i]; //第几等奖
                int count2 = x[i+1]; //第几等奖人数
                levelSet = new HashSet<Integer>();
                do {
                    int ranInt = RandomUtil.randomInt(num);
                    if(lottery.contains(ranInt)){
                        levelSet.add(ranInt);
                        lottery.remove(ranInt);
                    }
                    
                } while (levelSet.size() < count2);
                
                map.put(level, levelSet);
            }
            try {
                if(map.size()>0){
                    File file = new File("C:\\Users\\Jenkin\\Desktop\\获奖名单.txt");
                    if(!file.exists()){
                        file.createNewFile();  
                    }
                    StringBuffer sb = new StringBuffer();
                    for(int k = 0 ;k<length ;k+=2){
                        String str = "---------- " + change(x[k]) +"等奖  ------------";
                        sb.append(str + "\r\n");
                        System.out.println(str);
                        sb.append(print(map.get( x[k])));     
                    }
                    
                    FileWriter fw = new FileWriter("C:\\Users\\Jenkin\\Desktop\\获奖名单.txt", false);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.print(sb.toString());
                    pw.flush();
                    pw.close();
                    fw.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> String print(Set<T> set){
        StringBuffer sb = new StringBuffer();
        if(set != null && set.size()>0){
            int i = 1;
            for(Object obj : set){
                int x = (int)obj + 1;
                String str = "----第"+ i++ +"个：" + x + "号";
                sb.append(str + "\r\n");
                System.out.println(str);
            }
        }
        return sb.toString();
    }
    
    public static String change(int i){
        String result = "";
        switch (i) {
        case 1:
            result = "一";
            break;
        case 2:
            result = "二";
            break;
        case 3:
            result = "三";
            break;
        case 4:
            result = "四";
            break;
        case 5:
            result = "五";
            break;
        case 6:
            result = "六";
            break;
        case 7:
            result = "七";
            break;
        case 8:
            result = "八";
            break;
        case 9:
            result = "九";
            break;
        default:
            break;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        lotteryDraw(40, 20, 1, 1, 2, 3, 3, 6, 4, 10);
    }

}
