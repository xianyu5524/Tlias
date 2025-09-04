package com.tliasservice.service;

import com.tliasservice.pojo.Emp;
import com.tliasservice.pojo.EmpQueryParam;
import com.tliasservice.pojo.LoginInfo;
import com.tliasservice.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> queryByPage(EmpQueryParam e);

    void deleteById(List<Integer> ids);

    void save(Emp emp);

    Emp queryById(Integer id);

    void update(Emp emp);

    List<Emp> queryAllList();

    LoginInfo login(Emp emp);
}
