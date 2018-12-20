package com.guojiang.test;

import com.google.gson.Gson;
import com.guojiang.bean.NetCarDetailsData;
import com.guojiang.bean.NetCarMaketDetails;
import com.guojiang.bean.NetCarSizeDetail;
import com.guojiang.dao.NetCarDetailsDataMapper;
import com.guojiang.dao.NetCarMaketDetailsMapper;
import com.guojiang.dao.NetCarSizeDetailMapper;
import com.guojiang.netJson.NetCarDetailsDataJson;
import com.guojiang.netJson.NetCarMaketDetailsJson;
import com.guojiang.netJson.NetCarSizeDetailJson;
import com.guojiang.util.CommonConst;
import com.guojiang.util.MyHttp;
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
public class NetCarSizeDetailTest {

    @Autowired
    private NetCarSizeDetailMapper netCarSizeDetailMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testFindByUsedUid(){
        Gson gson = new Gson();
        System.out.println(netCarSizeDetailMapper);
        String value = readToString("D:/JavaWeb/MyCar_Shop_JavaWeb/MyCarShop/src/main/resources/qingke.txt");
        NetCarSizeDetailJson data = gson.fromJson(value,NetCarSizeDetailJson.class);
        System.out.println(data.serieslist.size());
        for (int i=0;i<data.serieslist.size();i++){
            NetCarSizeDetail sqlData = new NetCarSizeDetail();
            sqlData.setNsdBrandid(data.serieslist.get(i).brandid);
            sqlData.setNsdFctid(data.serieslist.get(i).fctid);
            sqlData.setNsdFctname(data.serieslist.get(i).fctname);
            sqlData.setNsdLogo(data.serieslist.get(i).logo);
            sqlData.setNsdOrder(data.serieslist.get(i).order);
            sqlData.setNsdSeriesid(data.serieslist.get(i).seriesid);
            sqlData.setNsdSeriesname(data.serieslist.get(i).seriesname);
            sqlData.setNsdSizeId(12);
            sqlData.setNsdState(data.serieslist.get(i).state);
            netCarSizeDetailMapper.insert(sqlData);
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

    @Test
    public void TT(){
        String back = MyHttp.sendGet(CommonConst.PlathUrl+CommonConst.getCarOperationMethod,"oderId="+1+"&"+"getCarUserId="+1);
        System.out.println(back);
    }

}