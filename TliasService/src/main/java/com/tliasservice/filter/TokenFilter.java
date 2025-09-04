package com.tliasservice.filter;

import com.tliasservice.util.JwtUtils;
import com.tliasservice.util.ThreadLocalUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURL().toString();

        if(url.contains("login")){
            log.info("登录请求，直接放行");
            filterChain.doFilter(req,res);
            return;
        }

        String jwt = req.getHeader("token");

        if(!StringUtils.hasLength(jwt)){
            log.info("获取的令牌为空，返回错误结果");
            res.setStatus(401);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer id= (Integer) claims.get("id");
            ThreadLocalUtils.setCurrentLocal(id);
            log.info("解析成功，且将{}存入当前线程的ThreadLocalMap中",id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回错误结果");
            res.setStatus(401);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(req,res);

        log.info("已将ThreadLocalMap中的数据删除");
        ThreadLocalUtils.remove();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
