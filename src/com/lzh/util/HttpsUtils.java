package com.lzh.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;

public class HttpsUtils {
    
    /**
     * 拼接URL参数
     * 
     * @param url
     * @param param
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String parseParam(String url, LinkedHashMap<String, String> param, String encode) throws UnsupportedEncodingException {
        if (param != null && !param.isEmpty()) {
            StringBuilder out = StringUtils.isBlank(url) ? new StringBuilder() : new StringBuilder(url).append(url.contains("?") ? "&" : "?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                out.append(entry.getKey());
                out.append("=");
                out.append(StringUtils.isBlank(encode) ? entry.getValue() : URLEncoder.encode(entry.getValue(), encode));
                out.append("&");
            }
            return StringUtils.substringBeforeLast(out.toString(), "&");
        }
        return url;
    }

    /**
     * content-type类型为xml方式发送post请求
     * 
     * @param urlPath
     * @param data
     * @param charSet
     * @return
     */
    public static String postForm(String urlPath, LinkedHashMap<String, String> param, LinkedHashMap<String, String> headerMap, String charSet) {
        String result = httpPostData(urlPath, param, headerMap, charSet, "POST");
        return result;
    }

    public static String getForm(String urlPath, LinkedHashMap<String, String> param, LinkedHashMap<String, String> headerMap, String charSet) {
        String result = httpPostData(urlPath, param, headerMap, charSet, "GET");
        return result;
    }
  
    private static String httpPostData(String urlPath, LinkedHashMap<String, String> param, LinkedHashMap<String, String> headerMap, String charSet,
            String requestMethod) {
        String result = null;
        URL url = null;
        HttpsURLConnection httspurlconnection = null;
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        try {

            // GET请求，拼接参数
            if (param != null && !param.isEmpty() && StringUtils.equals(requestMethod, "GET")) {
                urlPath = parseParam(urlPath, param, charSet);
            }

            url = new URL(urlPath);
            httspurlconnection = (HttpsURLConnection) url.openConnection();
            //httpurlconnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
            httspurlconnection.setDoInput(true);
            httspurlconnection.setDoOutput(true);

            // 设置通用的请求属性
            httspurlconnection.setRequestProperty("accept", "*/*");
            httspurlconnection.setRequestProperty("connection", "Keep-Alive");
            httspurlconnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 覆盖通用的请求属性
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    httspurlconnection.setRequestProperty(key, headerMap.get(key));
                }
            }

            httspurlconnection.setRequestMethod(requestMethod);

            httspurlconnection.connect();
            if (param != null && !param.isEmpty() && StringUtils.equals(requestMethod, "POST")) {
                DataOutputStream dos = new DataOutputStream(httspurlconnection.getOutputStream());
                dos.writeBytes(parseParam(null, param, null));
                dos.flush();
                dos.close();
            }

            int code = httspurlconnection.getResponseCode();

            if (code == 200) {
                System.out.println("***********************************ResponseCode:" + code);
                // 读取响应
                int length = (int) httspurlconnection.getContentLength();// 获取长度
                InputStream is = httspurlconnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = builder.toString();
            } else {
                System.out.println("http响应出错-----ResponseCode：" + code);
                int length = (int) httspurlconnection.getContentLength();// 获取长度
                InputStream is = httspurlconnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            url = null;
            if (httspurlconnection != null) {
                httspurlconnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("**********result*******:\n" + result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        String urlPath = "https://www.chinapopin.com/rnauth/oauth2/token";
        String result = "";
        LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
        String client_id = "0c7b07a254ab4a80980ded6393a6803a";
        String grant_type = "password";
        String client_secret = "935a3476b9514305a32ca57269eea5de";
        String provision_key = "53c51c779967450d8febfb391a7b7550";
        String authenticated_userid = "business_user";
        param.put("client_id", client_id);
        param.put("grant_type", grant_type);
        param.put("client_secret", client_secret);
        param.put("scope", "");
        param.put("provision_key", provision_key);
        param.put("authenticated_userid", authenticated_userid);

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<String, String>();
        headerMap.put("accept", "*/*");
        headerMap.put("connection", "Keep-Alive");
        headerMap.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        result = httpPostData(urlPath, param, headerMap, "UTF-8", "POST");
        System.out.println(result);
    }

}
