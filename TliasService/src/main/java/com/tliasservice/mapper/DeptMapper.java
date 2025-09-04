package com.tliasservice.mapper;

import com.tliasservice.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("SELECT id,name,create_time,update_time FROM dept ORDER BY update_time DESC;")
    List<Dept> findAll();

    @Delete("DELETE FROM dept WHERE id=#{id}")
    void deleteById(Integer id);

    @Insert("INSERT INTO dept(name,create_time,update_time) VALUES(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("SELECT * FROM dept WHERE id=#{id}")
    Dept findById(Integer id);

    @Update("UPDATE dept SET name=#{name},update_time=#{updateTime} WHERE id=#{id}")
    void update(Dept dept);
}
