package com.tliasservice.controller;

import com.tliasservice.pojo.Emp;
import com.tliasservice.pojo.LoginInfo;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        LoginInfo loginInfo=empService.login(emp);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        }

        return Result.error("用户名或密码错误");
    }
}
