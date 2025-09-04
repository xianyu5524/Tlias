package com.tliasservice.controller;

import com.tliasservice.anno.Log;
import com.tliasservice.pojo.Emp;
import com.tliasservice.pojo.EmpQueryParam;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpsController {
    @Autowired
    private EmpService empsService;

    @GetMapping
    public Result queryPageList(EmpQueryParam empQueryParam) {
        log.info("分页查询请求参数：{}",empQueryParam);
        PageResult<Emp> pageResult=empsService.queryByPage(empQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result queryList(){
        log.info("查询所有员工");
        List<Emp> allList=empsService.queryAllList();
        return Result.success(allList);
    }

    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除用户：ids={}", ids);
        empsService.deleteById(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("添加用户：emp={}",emp);
        empsService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id){
        log.info("查找用户：id={}",id);
        Emp emp=empsService.queryById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改用户信息：emp={}",emp);
        empsService.update(emp);
        return Result.success();
    }
}
