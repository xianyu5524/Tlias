package com.tliasservice.service;

import com.tliasservice.pojo.LogQueryParam;
import com.tliasservice.pojo.OperateLog;
import com.tliasservice.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> queryByPage(LogQueryParam param);
}
