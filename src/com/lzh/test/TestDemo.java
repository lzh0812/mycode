package com.lzh.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lzh.test.bean.User;
import com.lzh.test.factory.BeanFactory;

public class TestDemo {
    public static void main(String[] args) throws Exception{
        TestDemo.test1();
    }
    
    public static void test1() throws InstantiationException, IllegalAccessException{
        List<User>  users = new ArrayList<User>();
        User u1 = new User();
        u1.setUserName("111111");
        u1.setPassword("2222222");
        u1.setAge(12);
        users.add(u1);
        
        User u2 = new User();
        u2.setUserName("3333333");
        u2.setPassword("2222222");
        u2.setAge(14);
        users.add(u2);
        
        Collections.sort(users,new Comparator<User>(){
            @Override
            public int compare(User a1,User a2){
                return a2.getAge() - a1.getAge();
            }
        });
        
        System.out.println(users);
        
        
        
    }
    
    
    
    
    
    
}
