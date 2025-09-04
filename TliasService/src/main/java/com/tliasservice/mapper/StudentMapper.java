package com.tliasservice.mapper;

import com.tliasservice.pojo.Student;
import com.tliasservice.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select count(*) from student where clazz_id=#{cId}")
    Integer countStudentsByClazzId(Integer cId);

    List<Student> pageList(StudentQueryParam s);

    @Insert("insert into student values (null,#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    void add(Student student);

    @Select("select * from student where id=#{id}")
    Student queryById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void violation(Integer id, Short score);

    List<Map<String, Object>> countStudentDegreeData();

    List<Map<String, Object>> countStudentClazzData();
}
