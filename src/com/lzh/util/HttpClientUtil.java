package com.lzh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class HttpClientUtil {
    /** 请求方式：GET */
    public static final String METHOD_GET = "GET";

    /** 请求方式：POST */
    public static final String METHOD_POST = "POST";

    /** 默认字符集：UTF-8 */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 从URL中获取参数信息
     * 
     * @param url
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> getParamFromUrl(String url, String decode) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<String, String>();

        if (url.matches("[^\\?&=\\s]+\\?(&?[^\\?&=\\s]+=[^\\?&\\s]+)+")) {
            for (String p : url.substring(url.indexOf("?") + 1).split("&")) {
                if (StringUtils.isNotBlank(p)) {
                    String key = p.substring(0, p.indexOf("="));
                    String value = p.substring(p.indexOf("=") + 1);
                    param.put(key, StringUtils.isBlank(decode) ? value : URLDecoder.decode(value, decode));
                }
            }
        }

        return param;
    }

    /**
     * 将参数拼接至URL
     * 
     * @param url
     * @param param
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String addParamToUrl(String url, Map<String, String> param, String encode) throws UnsupportedEncodingException {
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
     * 封装发送请求的基本方法
     * 
     * @param url 请求地址。
     * @param param 输入参数
     * @param headerMap 覆盖默认请求头map
     * @param requestMethod 请求方法。POST, GET
     * @param charset
     * @param connTimeout 连接超时时间（单位：秒）
     * @param readTimeout 读取数据超时时间（单位：秒）
     * @return
     */
    public static String send(String url, Map<String, String> param, Map<String, String> headerMap, String requestMethod, String charset, int connTimeout,
            int readTimeout) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            // GET请求，拼接参数
            if (param != null && !param.isEmpty() && StringUtils.equals(requestMethod, METHOD_GET)) {
                url = addParamToUrl(url, param, charset);
            }
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 覆盖通用的请求属性
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    conn.setRequestProperty(key, headerMap.get(key));
                }
            }
            // 设置请求方法
            conn.setRequestMethod(requestMethod);

            // 定义连接超时时间，读取数据超时时间，默认初始值为60s
            int CONN_TIME_OUT = 60, READ_TIME_OUT = 60;
            if (connTimeout > 0) {
                CONN_TIME_OUT = connTimeout;
            }

            if (readTimeout > 0) {
                READ_TIME_OUT = readTimeout;
            }

            // 设置连接主机超时时间（单位：毫秒）
            conn.setConnectTimeout(CONN_TIME_OUT * 1000);
            // 设置从主机读取数据超时时间（单位：毫秒）
            conn.setReadTimeout(READ_TIME_OUT * 1000);
            // 设置从HttpURLConnection读入
            conn.setDoInput(true);

            // POST请求，拼接参数
            if (param != null && !param.isEmpty() && StringUtils.equals(requestMethod, METHOD_POST)) {
                // 设置向HttpURLConnection输出
                conn.setDoOutput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 从URL中获取参数
                param.putAll(getParamFromUrl(url, charset));
                // 发送请求参数
                out.print(addParamToUrl(null, param, charset));
                // flush输出流的缓冲
                out.flush();
            }

            // 调用conn.getInputStream()，就将请求发送出去
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭输出流
            if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 向指定URL发送POST方式请求
     * 
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, Map<String, String> param) {
        return send(url, param, null, METHOD_POST, DEFAULT_CHARSET, -1, -1);
    }

    /**
     * 向指定URL发送POST方式请求，自定义连接超时时间
     * 
     * @param url
     * @param param
     * @param connTimeout 定义连接超时时间（单位：秒）
     * @param readTimeout 定义读取数据超时时间（单位：秒）
     * @return
     */
    public static String post(String url, Map<String, String> param, int connTimeout, int readTimeout) {
        return send(url, param, null, METHOD_POST, DEFAULT_CHARSET, connTimeout, readTimeout);
    }

    /**
     * 向指定URL发送GET方式请求
     * 
     * @param url
     * @param param
     * @return
     */
    public static String get(String url, Map<String, String> param) {
        return send(url, param, null, METHOD_GET, DEFAULT_CHARSET, -1, -1);
    }

    /**
     * 向指定URL发送GET方式请求，自定义连接超时时间
     * 
     * @param url
     * @param param
     * @param connTimeout 定义连接超时时间（单位：秒）
     * @param readTimeout 定义读取数据超时时间（单位：秒）
     * @return
     */
    public static String get(String url, Map<String, String> param, int connTimeout, int readTimeout) {
        return send(url, param, null, METHOD_GET, DEFAULT_CHARSET, connTimeout, readTimeout);
    }

    /**
     * 通过Response向指定URL跳转
     * 
     * @param response
     * @param url
     * @param param
     * @param method
     */
    public static void jump(HttpServletResponse response, String url, Map<String, String> param, String method) {
        StringBuilder html = new StringBuilder();

        html.append("<!Doctype html><html><body>");

        if (METHOD_GET.equalsIgnoreCase(method) && (param == null || param.isEmpty())) {
            html.append("<script type='text/javascript'>");
            html.append("window.location='").append(url).append("'");
            html.append("</script>");
        } else {
            String formName = RandomUtil.randomUUID();

            html.append("<form id='").append(formName).append("' action='").append(url.replaceAll("\\?.+", "")).append("' method='").append(method)
                    .append("'>");

            try {
                (param == null ? param = new HashMap<String, String>() : param).putAll(getParamFromUrl(url, DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (METHOD_POST.equalsIgnoreCase(method) && param.isEmpty()) {
                param.put(RandomUtil.randomUUID(), RandomUtil.randomUUID());
            }

            for (Map.Entry<String, String> entry : param.entrySet()) {
                html.append("<input type='hidden' name='").append(entry.getKey()).append("' value='").append(entry.getValue()).append("' />");
            }

            html.append("</form>");

            html.append("<script type='text/javascript'>");
            html.append("document.getElementById('").append(formName).append("').submit();");
            html.append("</script>");
        }

        html.append("</body></html>");

        try {
            ResponseUtil.write(response, html.toString(), "Jump-By", "html");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
