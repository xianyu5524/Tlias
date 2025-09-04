package com.tliasservice.mapper;

import com.tliasservice.pojo.Emp;
import com.tliasservice.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    List<Emp> pageList(EmpQueryParam empQueryParam);

    void deleteByIds(List<Integer> ids);

    @Options(useGeneratedKeys=true,keyProperty = "id")
    @Insert("INSERT INTO emp VALUES(#{id},#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    @Select("select * from emp")
    List<Emp> list();

    @Select("select count(*) from emp where dept_id=#{id}")
    Integer countEmpByDeptId(Integer id);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getUserByUserNameAndPassword(Emp emp);
}
