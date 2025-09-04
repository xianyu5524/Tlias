package com.tliasservice.controller;

import com.tliasservice.anno.Log;
import com.tliasservice.pojo.Dept;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //get查询所有部门
    @GetMapping
    public Result list(){
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //delete通过请求参数id删除部门
    @Log
    @DeleteMapping
    public Result delete(@RequestParam("id") Integer id){
        log.info("根据请求参数id删除部门，id：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //post通过json添加部门
    @Log
    @PostMapping
    public Result save(@RequestBody Dept dept){
        log.info("根据请求体json数据添加部门，json：{}",dept);
        deptService.save(dept);
        return Result.success();
    }

    //get通过路径id查询部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据请求路径id查询部门，id：{}",id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }

    //put通过json更新部门名称
    @Log
    @PutMapping
    public Result updateByIdAndName(@RequestBody Dept dept){
        log.info("根据请求体json数据修改部门信息，json：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
