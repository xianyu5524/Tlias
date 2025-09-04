package com.tliasservice.controller;

import com.tliasservice.pojo.LogQueryParam;
import com.tliasservice.pojo.OperateLog;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result queryPageList(LogQueryParam param){
        log.info("操作日志分页查询");
        PageResult<OperateLog> result=logService.queryByPage(param);
        return Result.success(result);
    }
}
