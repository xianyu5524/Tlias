package com.tliasservice.aop;

import com.tliasservice.mapper.LogMapper;
import com.tliasservice.pojo.OperateLog;
import com.tliasservice.util.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class OperationLogAspect {

    @Autowired
    LogMapper logMapper;

    @Around("@annotation(com.tliasservice.anno.Log)")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = pj.proceed();

        long endTime = System.currentTimeMillis();

        long costTime = endTime - startTime;

        //构建日志
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(pj.getTarget().getClass().getName());
        operateLog.setMethodName(pj.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pj.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);

        log.info("记录操作日志：{}",operateLog);
        logMapper.insert(operateLog);

        return result;
    }

    // 获取当前用户ID
    private int getCurrentUserId() {
        //从认证信息中获取当前登录用户的ID
        return ThreadLocalUtils.getCurrentId();
    }
}
