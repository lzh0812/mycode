package com.lzh.test;

public class Test3 {
    public static void main(String[] args) {

        for(int i = 1;i<10 ;i++){
            for(int j=1;j<10;j++){
                if((i + 2*j) == 10  ){
                    
                    System.out.printf("\ni=%d,j=%d",i,j);
                    
                }
                
                
            }
            
            
        }
        
    }
    
    public void dLock(){
        
        synchronized (this) {
            
        }
        
        
        
        
    }

}
