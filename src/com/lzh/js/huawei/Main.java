package com.lzh.js.huawei;

import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in); 
        for(int i = 0; i<10; i++){
            String ori = sc.nextLine(); 
            System.out.println(getBnum(ori));
        }
        
    }
    
    public static String replaceStr(String str){
        if(!str.contains(".") && !str.contains("-")){
            return "";
        }
        return str.replace(".", "1").replace("-", "0");
    }
    
    public static String getBnum(String str){
        
        String replaceText = replaceStr(str);
        
        if("".equals(replaceText)){
            return "";
        }
        
        String result = "";
        
        for(String s : replaceText.split("#")){
            if(Integer.parseInt(s, 2)>51){
                return "ERROR";
            }
            result = result + toStr(Integer.parseInt(s, 2));
        }
        
        return result;
    }
    
    public static String toBnum(String str){
        
        char[] c = str.toCharArray();
        
        int length = c.length;
        String bNum = "";
        for(int i = 0; i< length -1;i++){
            bNum = bNum + toNum(String.valueOf(c[i]) );
            if(toNum(String.valueOf(c[i])).equals("E")){
                return "ERROR";
            }
        }
        
        return bNum;
    }
    
    
    
    public static String toNum(String intText){
        String num = "0";
        
        if(intText.equals(".")){
            num = "1";
        } else if(intText.equals("-")){
            num = "0";
        } else{
            num = "E";
        }
        return num;
    }
    
    public static String toStr(int ori){
        String a = "";
        switch (ori) {
        case 0:
            a = "F";
            break;
        case 1:
            a = "G";
            break;
        case 2:
            a = "R";
            break;
        case 3:
            a = "S";
            break;
        case 4:
            a = "T";
            break;
        case 5:
            a = "L";
            break;
        case 6:
            a = "M";
            break;
        case 7:
            a = "N";
            break;
        case 8:
            a = "O";
            break;
        case 9:
            a = "P";
            break;
        case 10:
            a = "Q";
            break;
        case 11:
            a = "W";
            break;
        case 12:
            a = "X";
            break;
        case 13:
            a = "Y";
            break;
        case 14:
            a = "Z";
            break;
        case 15:
            a = "U";
            break;
        case 16:
            a = "A";
            break;
        case 17:
            a = "G";
            break;
        case 18:
            a = "H";
            break;
        case 19:
            a = "I";
            break;
        case 20:
            a = "J";
            break;
        case 21:
            a = "K";
            break;
        case 22:
            a = "B";
            break;
        case 23:
            a = "C";
            break;
        case 24:
            a = "D";
            break;
        case 25:
            a = "E";
            break;
        case 26:
            a = "l";
            break;
        case 27:
            a = "m";
            break;
        case 28:
            a = "n";
            break;
        case 29:
            a = "o";
            break;
        case 30:
            a = "p";
            break;
        case 31:
            a = "i";
            break;
        case 32:
            a = "j";
            break;
        case 33:
            a = "k";
            break;
        case 34:
            a = "f";
            break;
        case 35:
            a = "g";
            break;
        case 36:
            a = "h";
            break;
        case 37:
            a = "a";
            break;
        case 38:
            a = "b";
            break;
        case 39:
            a = "c";
            break;
        case 40:
            a = "d";
            break;
        case 41:
            a = "e";
            break;
        case 42:
            a = "q";
            break;
        case 43:
            a = "r";
            break;
        case 44:
            a = "w";
            break;
        case 45:
            a = "x";
            break;
        case 46:
            a = "y";
            break;
        case 47:
            a = "z";
            break;
        case 48:
            a = "s";
            break;
        case 49:
            a = "t";
            break;
        case 50:
            a = "u";
            break;
        case 51:
            a = "v";
            break;

        default:
            break;
        }
        
        
        
        return a;
        
        
        
        
        
        
    }
}
