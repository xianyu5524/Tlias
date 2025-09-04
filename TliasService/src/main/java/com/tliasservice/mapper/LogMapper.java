package com.tliasservice.mapper;

import com.tliasservice.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Insert("insert into operate_log values (null,#{operateEmpId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog log);

    @Select("select o.*,e.name as operate_emp_name from operate_log as o left join emp as e on o.operate_emp_id=e.id")
    List<OperateLog> pageList();
}
