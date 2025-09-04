package com.tliasservice.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name;        //学员姓名
    private Integer degree;     //学历(1:初中,2:高中,3:大专,4:本科,5:硕士,6:博士)
    private Integer clazzId;    //班级ID
    private Integer page;       //分页查询的页码，如果未指定，默认为1
    private Integer pageSize;   //分页查询的每页记录数，如果未指定，默认为10
}
