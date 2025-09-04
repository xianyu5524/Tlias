package com.tliasservice.service.impl;

import com.tliasservice.mapper.EmpMapper;
import com.tliasservice.mapper.StudentMapper;
import com.tliasservice.pojo.ClazzOption;
import com.tliasservice.pojo.JobOption;
import com.tliasservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> data = empMapper.countEmpJobData();

        List<Object> pos = data.stream().map(mapData -> mapData.get("pos")).toList();
        List<Object> total = data.stream().map(mapDate -> mapDate.get("total")).toList();

        return new JobOption(pos,total);

    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String,Object>> data=studentMapper.countStudentClazzData();
        List<Object> className = data.stream().map(mapData -> mapData.get("className")).toList();
        List<Object> total = data.stream().map(mapData -> mapData.get("total")).toList();

        return new ClazzOption(className,total);
    }
}
