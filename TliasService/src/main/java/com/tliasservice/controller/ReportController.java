package com.tliasservice.controller;

import com.tliasservice.pojo.ClazzOption;
import com.tliasservice.pojo.JobOption;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工数量");
        JobOption empJobData = reportService.getEmpJobData();
        return Result.success(empJobData);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map<String,Object>> empGenderData=reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String,Object>> studentDegreeData=reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数信息");
        ClazzOption studentClazzDate=reportService.getStudentCountData();
        return Result.success(studentClazzDate);
    }
}
