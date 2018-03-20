package com.lzh.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class WebServiceUtil {

    // 阿帕奇HttpClient方式调用
    public static HashMap<String, String> getData(String serviceCode, String xmlPara) {
        HashMap<String, String> res = new HashMap<>();
        String endpoint = "http://124.205.248.2:8080/eSales/esales.asmx?WSDL";
        PostMethod postMethod = new PostMethod(endpoint);
        byte[] b;
        try {
            b = xmlPara.getBytes("utf-8");
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length, "application/soap+xml; charset=utf-8");
            // 把Soap请求数据添加到PostMethod中
            postMethod.setRequestEntity(re);

            // 生成一个HttpClient对象，并发出postMethod请求
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (200 == statusCode) {
                String getServerData = postMethod.getResponseBodyAsString();
                // System.out.println("----->"+getServerData);
                // 获取返回值状态标识，标识为0：成功；非0：失败
                res.put("status", "0");
                res.put("msg", getServerData);
            }
        } catch (Exception e) {
            res.put("status", "1");
            res.put("msg", e.toString());
            e.printStackTrace();
        }
        return res;
    }

    // axis 方式调用
    public static String wsWithAxis(String wsdlUrl, String actionURI, Map<String, Object> paramMap) {
        Object[] params = null;
        int index = actionURI.lastIndexOf("/") + 1;
        String action = actionURI.substring(index);
        String nameSpaceUri = actionURI.substring(0, index);
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(wsdlUrl);
            call.setSOAPActionURI(actionURI);
            call.setOperationName(new QName(nameSpaceUri, action));
            for (String key : paramMap.keySet()) {
                call.addParameter(key, XMLType.XSD_STRING, ParameterMode.IN);
            }
            if (paramMap.size() > 0) {
                params = paramMap.values().toArray();
            }
            call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
            String re = (String) call.invoke(params);
            return re;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String wsWithAxis2(String name, String cetfNo) {
        String url = "http://172.25.130.13:8080/GSJLCore/services/huncaLCoreService?wsdl";
        String userName = "ga.population.username";
        String password = "ga.population.password";
        String method = "verifyNaturalPerson";

        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(url);
            Options options = serviceClient.getOptions();
            // 确定目标服务地址
            options.setTo(targetEPR);
            // 设置超时时间
            options.setTimeOutInMilliSeconds(30000);

            QName qname = new QName(url, method);
            // 指定方法的参数值
            Object[] parameters = new Object[] { userName, password, cetfNo, name };

            // 指定方法返回值的数据类型的Class对象
            @SuppressWarnings("rawtypes")
            Class[] returnTypes = new Class[] { String.class };

            // 调用方法二 方法并输出该方法的返回值
            Object[] response = serviceClient.invokeBlocking(qname, parameters, returnTypes);
            return (String) response[0];
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }
}
