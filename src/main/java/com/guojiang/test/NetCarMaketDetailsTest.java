package com.guojiang.test;

import com.google.gson.Gson;
import com.guojiang.bean.NetCarDetailsData;
import com.guojiang.bean.NetCarMaketDetails;
import com.guojiang.dao.NetCarDetailsDataMapper;
import com.guojiang.dao.NetCarMaketDetailsMapper;
import com.guojiang.netJson.NetCarDetailsDataJson;
import com.guojiang.netJson.NetCarMaketDetailsJson;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class NetCarMaketDetailsTest {

    private String url = "https://car.autohome.com.cn/duibi/ashx/specComparehandler.ashx?callback=jsonpCallback&type=1&seriesid=";

    private String urlLast = "&format=json&_=1540971915992";

    @Autowired
    private NetCarMaketDetailsMapper netCarMaketDetailsMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testFindByUsedUid(){
        Gson gson = new Gson();
        System.out.println(netCarMaketDetailsMapper);
        String value = readToString("D:/JavaWeb/MyCar_Shop_JavaWeb/MyCarShop/src/main/resources/subcar.txt");
        NetCarDetailsDataJson data = gson.fromJson(value,NetCarDetailsDataJson.class);
        System.out.println(data.subCar.size());
        for (int i =0;i<data.subCar.size();i++){
            for (int j=0;j<data.subCar.get(i).List.size();j++){
                for (int k=0;k<data.subCar.get(i).List.get(j).List.size();k++){
                    String result= sendGet(data.subCar.get(i).List.get(j).List.get(k).I);
                    result = getValue(result);
                    NetCarMaketDetailsJson jsondata = gson.fromJson(result, NetCarMaketDetailsJson.class);
                    NetCarMaketDetails sqlData = new NetCarMaketDetails();
                    sqlData.setNmdI(jsondata.I);
                    sqlData.setNmdJson(gson.toJson(jsondata.List));
                    netCarMaketDetailsMapper.insert(sqlData);
                }
            }
        }

    }

    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public String sendGet(Integer serialId) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + serialId + urlLast;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));//转成utf-8格式
            StringBuilder response = new StringBuilder();
            String lineaa;
            while((lineaa=reader.readLine())!=null){
                response.append(lineaa);
            }
            result = response.toString();

        } catch (Exception e) {
            System.out.println("get发送短信报错" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public String getValue(String result){
        String a = result.substring("jsonpCallback(".length(),result.length()-1);
        System.out.println(a);
        return a;
    }

}