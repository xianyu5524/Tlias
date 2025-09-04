package com.tliasservice.controller;

import com.tliasservice.anno.Log;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Result;
import com.tliasservice.pojo.Student;
import com.tliasservice.pojo.StudentQueryParam;
import com.tliasservice.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result queryPageList(StudentQueryParam studentQueryParam){
        log.info("分页查询学员：{}",studentQueryParam);
        PageResult<Student> pageList=studentService.queryByPage(studentQueryParam);
        return Result.success(pageList);
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id){
        log.info("查找学员：{}",id);
        Student student=studentService.queryById(id);
        return Result.success(student);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除人员：{}",ids);
        studentService.deleteById(ids);
        return Result.success();
    }

    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Short score){
        log.info("违纪处理：{} 扣{}分",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
