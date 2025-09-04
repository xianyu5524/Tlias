package com.tliasservice.service;

import com.tliasservice.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void save(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
