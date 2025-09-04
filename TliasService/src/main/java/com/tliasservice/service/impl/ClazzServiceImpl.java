package com.tliasservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tliasservice.exception.BusinessException;
import com.tliasservice.mapper.ClazzMapper;
import com.tliasservice.mapper.StudentMapper;
import com.tliasservice.pojo.Clazz;
import com.tliasservice.pojo.ClazzQueryParam;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> queryByPage(ClazzQueryParam c) {
        PageHelper.startPage(c.getPage(),c.getPageSize());

        List<Clazz> clazzList =clazzMapper.pageList(c);

        Page<Clazz> p= (Page<Clazz>) clazzList;

        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz queryById(Integer id) {
        return clazzMapper.getInfo(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public void delete(Integer id) {

        if(studentMapper.countStudentsByClazzId(id)>0){
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }

        clazzMapper.delete(id);
    }

    @Override
    public List<Clazz> queryAllList() {
        return clazzMapper.list();
    }
}
