package com.tliasservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tliasservice.mapper.StudentMapper;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Student;
import com.tliasservice.pojo.StudentQueryParam;
import com.tliasservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> queryByPage(StudentQueryParam s) {
        PageHelper.startPage(s.getPage(),s.getPageSize());
        List<Student> pageList=studentMapper.pageList(s);
        Page<Student> page= (Page<Student>) pageList;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        if(student.getViolationCount()==null&&student.getViolationScore()==null){
            student.setViolationCount((short) 0);
            student.setViolationScore((short) 0);
        }

        if(student.getClazzId()==null){
            student.setClazzId(0);
        }
        studentMapper.add(student);
    }

    @Override
    public Student queryById(Integer id) {
        return studentMapper.queryById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void violation(Integer id, Short score) {
        studentMapper.violation(id,score);
    }


}
