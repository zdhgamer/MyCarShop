package com.guojiang.controller;

import com.guojiang.bean.TestTable;
import com.guojiang.service.TestTableService;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestTableController {

    @Autowired
    private TestTableService testTableService;


    @ResponseBody
    @RequestMapping("/api/TestAPI")
    public ResultMsg TestAPI(@RequestParam("test") Integer test){
        ResultMsg result = new ResultMsg();
        System.out.println(test);
        TestTable table = testTableService.selectByPrimaryKey(1);
        System.out.println(table.gettDd());
        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,null);
        return result;
    }

}
