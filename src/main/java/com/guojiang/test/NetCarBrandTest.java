package com.guojiang.test;
import com.google.gson.Gson;
import com.guojiang.bean.NetCarBrand;
import com.guojiang.dao.NetCarBrandMapper;
import com.guojiang.netJson.NetCarBrandJson;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class NetCarBrandTest {

    @Autowired
    private NetCarBrandMapper netCarBrandMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testFindByUsedUid(){
        Gson gson = new Gson();
        System.out.println(netCarBrandMapper);
        String value = readToString("D:/JavaWeb/MyCar_Shop_JavaWeb/MyCarShop/src/main/resources/allcar.txt");
        System.out.println(value);
        NetCarBrandJson data = gson.fromJson(value,NetCarBrandJson.class);
        System.out.println(data.branditems.size());
        for (int i =0;i<data.branditems.size();i++){
            NetCarBrand sqlData = new NetCarBrand();
            sqlData.setNcbBfirstletter(data.branditems.get(i).bfirstletter);
            sqlData.setNcbLogopic(data.branditems.get(i).logo);
            sqlData.setNcbReturnid(data.branditems.get(i).id);
            sqlData.setNcdName(data.branditems.get(i).name);
            netCarBrandMapper.insert(sqlData);
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

}