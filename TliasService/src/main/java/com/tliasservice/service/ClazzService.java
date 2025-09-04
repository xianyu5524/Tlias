package com.tliasservice.service;

import com.tliasservice.pojo.Clazz;
import com.tliasservice.pojo.ClazzQueryParam;
import com.tliasservice.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> queryByPage(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz queryById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> queryAllList();
}
