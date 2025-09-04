package com.tliasservice.mapper;

import com.tliasservice.pojo.Clazz;
import com.tliasservice.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> pageList(ClazzQueryParam clazzQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into clazz values (null,#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    @Select("select * from clazz where id=#{id}")
    Clazz getInfo(Integer id);

    void updateById(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    @Select("select * from clazz")
    List<Clazz> list();
}
