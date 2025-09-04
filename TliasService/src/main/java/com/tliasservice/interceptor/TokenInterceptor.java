package com.tliasservice.interceptor;

import com.tliasservice.util.JwtUtils;
import com.tliasservice.util.ThreadLocalUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();

        if(url.contains("login")){
            log.info("登录请求，直接放行");
            return true;
        }

        String jwt = request.getHeader("token");

        if(!StringUtils.hasLength(jwt)){
            log.info("令牌为空，返回错误结果");
            response.setStatus(401);
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer id= (Integer) claims.get("id");
            ThreadLocalUtils.setCurrentLocal(id);
            log.info("解析成功，且将{}存入当前线程的ThreadLocalMap中",id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回错误结果");
            response.setStatus(401);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("已将ThreadLocalMap中的数据删除");
        ThreadLocalUtils.remove();
    }
}
