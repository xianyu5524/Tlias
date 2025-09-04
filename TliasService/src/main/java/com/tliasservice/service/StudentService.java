package com.tliasservice.service;

import com.tliasservice.pojo.PageResult;
import com.tliasservice.pojo.Student;
import com.tliasservice.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> queryByPage(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student queryById(Integer id);

    void update(Student student);

    void deleteById(List<Integer> ids);

    void violation(Integer id, Short score);
}
