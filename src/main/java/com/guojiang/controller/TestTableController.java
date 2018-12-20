package com.guojiang.controller;

import com.guojiang.bean.TestTable;
import com.guojiang.service.TestTableService;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class TestTableController {

    public static final Logger logger = LogManager.getLogger(TestTableController.class);

    @Autowired
    private TestTableService testTableService;

    @ResponseBody
    @RequestMapping("/api/TestAPI")
    public ResultMsg TestAPI(@RequestParam("test") Integer test){
        logger.info("-->TestAPI->"+"去你妈的");
        ResultMsg result = new ResultMsg();
        System.out.println(test);
        TestTable table = testTableService.selectByPrimaryKey(1);
        System.out.println(table.gettDd());
        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,null);
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/api/TestUpLoadImage", method= RequestMethod.POST)
    public ResultMsg TestUpLoadImage(@RequestParam(value="drivePic",required=false) MultipartFile drivePic){
        logger.info("-->TestUpLoadImage->"+"去你妈的");
        ResultMsg result = new ResultMsg();
        System.out.println(drivePic.getSize());
        return result;
    }

}
