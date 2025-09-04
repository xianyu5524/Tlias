package com.tliasservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tliasservice.mapper.LogMapper;
import com.tliasservice.pojo.LogQueryParam;
import com.tliasservice.pojo.OperateLog;
import com.tliasservice.pojo.PageResult;
import com.tliasservice.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult<OperateLog> queryByPage(LogQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());

        List<OperateLog> logList=logMapper.pageList();

        Page<OperateLog> p= (Page<OperateLog>) logList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}
