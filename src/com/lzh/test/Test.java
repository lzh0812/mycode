package com.lzh.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

import com.lzh.util.RandomUtil;

public class Test {

    public static String requestTokenService2() {

        String client_id = "0c7b07a254ab4a80980ded6393a6803a";
        String grant_type = "password";
        String client_secret = "935a3476b9514305a32ca57269eea5de";
        String provision_key = "53c51c779967450d8febfb391a7b7550";
        String authenticated_userid = "business_user";

        HttpsURLConnection httpConn = null;
        try {
            String urlPath = new String("https://www.chinapopin.com");
            URL url = new URL(urlPath);
            httpConn = (HttpsURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpConn.setConnectTimeout(15000);
            httpConn.setReadTimeout(30000);
            // httpConn.setRequestProperty("accept", "*/*");
            // httpConn.setRequestProperty("connection", "Keep-Alive");
            // httpConn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //
            httpConn.connect();
            DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
            dos.writeBytes("client_id=" + client_id + "&grant_type=" + grant_type + "&client_secret=" + client_secret + "&provision_key=" + provision_key
                    + "&authenticated_userid=" + authenticated_userid);
            dos.flush();
            dos.close();
            int resultCode = httpConn.getResponseCode();
            System.out.println(resultCode);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String readLine = "";
            StringBuffer sb = new StringBuffer();
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            System.out.println("****************" + resultCode + sb + "****************");
            responseReader.close();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                // JSONObject jsonObject = JSON.parseObject(sb.toString());
                // if (jsonObject != null) {
                // if (StringUtils.isNotEmpty(jsonObject.getString("access_token"))) {
                // logger.info("****************" + jsonObject.toString() + "****************");
                // return ResponseMessage.ok(jsonObject.toJSONString());
                // }
                // }
                // return ResponseMessage.error("��ȡtokenʧ��");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
                httpConn = null;
            }
        }
        return ("����token����ʧ��");
    }

    /**
     * @param num 抽奖范围
     * @param count 抽奖个数
     */
    public static Set<Integer> lotteryDrawSet(int num, int count) {

        Set<Integer> lottery = new HashSet<Integer>();

        do {
            lottery.add(RandomUtil.randomInt(num));
        } while (lottery.size() < count);

        return lottery;
    }

    /**
     * @param num 抽奖范围 1-num
     * @param count 抽奖格式 count个
     * @param x
     */
    public static void lotteryDraw(int num, int count, int... x) {
        Set<Integer> lottery = lotteryDrawSet(num, count);
        if (x.length == 0) {
            print(lottery);
        } else if (x.length % 2 != 0) { // 参数个数必须是2的倍数
            System.out.println("输入参数个数不对！");
        } else if (x.length > 2) {

            int length = x.length;
            int i;
            Map<Integer, Set<Integer>> map = new TreeMap<Integer, Set<Integer>>();
            Set<Integer> levelSet = new HashSet<Integer>();
            for (i = 0; i < length; i += 2) {
                int level = x[i]; // 第几等奖
                int count2 = x[i + 1]; // 第几等奖人数
                levelSet = new HashSet<Integer>();
                do {
                    int ranInt = RandomUtil.randomInt(num);
                    if (lottery.contains(ranInt)) {
                        levelSet.add(ranInt);
                        lottery.remove(ranInt);
                    }

                } while (levelSet.size() < count2);

                map.put(level, levelSet);
            }

            if (map.size() > 0) {
                for (int k = 0; k < length; k += 2) {
                    System.out.println("----------第" + x[k] + "等奖------------");
                    print(map.get(x[k]));
                }
            }
        }
    }

    public static <T> void print(Set<T> set) {
        if (set != null && set.size() > 0) {
            int i = 1;
            for (Object obj : set) {
                System.out.println("----第" + i++ + "个：" + obj);
            }
        }
    }

    public static void test1() throws ClassNotFoundException {
        Class c = Class.forName("com.lzh.test.bean.User");
        Field[] fs = c.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
        // 里边的每一个属性
        for (Field field : fs) {
            sb.append("\t");// 空格
            sb.append(Modifier.toString(field.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
            sb.append(field.getType().getSimpleName() + " ");// 属性的类型的名字
            sb.append(field.getName() + ";\n");// 属性的名字+回车
        }

        sb.append("}");

        System.out.println(sb);

    }
    
    volatile int i;
    public void add(){
        i++;     // 如果多线程循环调用add,就会涉及到一个复合操作，先要读取i，然后对i进行加一，最后把i的值写入内存
    }
    
    public void test2(){
        synchronized(Test.class){
            
            
            
        }
    }
    
    
    
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ClassNotFoundException {
        test1();
    }

}
