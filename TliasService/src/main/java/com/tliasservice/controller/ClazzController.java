package com.tliasservice.controller;

import com.tliasservice.anno.Log;
import com.tliasservice.pojo.Clazz;
import com.tliasservice.pojo.ClazzQueryParam;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clazzs")
@Slf4j
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result queryPageList(ClazzQueryParam clazzQueryParam){
        log.info("查询班级信息 ：{}",clazzQueryParam);
        PageResult<Clazz> pageResult=clazzService.queryByPage(clazzQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("list")
    public Result queryList(){
        log.info("查询所有班级信息");
        List<Clazz> allList=clazzService.queryAllList();
        return Result.success(allList);
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id){
        log.info("查询班级，id：{}",id);
        Clazz clazz=clazzService.queryById(id);
        return Result.success(clazz);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级：{}",id);
        clazzService.delete(id);
        return Result.success();
    }


}
