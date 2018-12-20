package com.guojiang.util;

import com.guojiang.controller.UOperationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class MyHttp {

    public static final Logger logger = LogManager.getLogger(MyHttp.class);

    /**
     * get发送
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("sendGet---》get发送Http出错" + e);
            e.printStackTrace();
            return result;
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return result;
            }
        }
        return result;
    }

    /**
     * post方式发送
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("sendPost---》post方式发送" + e);
            e.printStackTrace();
            return result;
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                return result;
            }
        }
        return result;
    }

    /**
     * 连接加密
     * @param str
     * @param charset
     * @return
     */
    public static String urlEncode(String str, String charset) {
        try {
            return java.net.URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
