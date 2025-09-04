package com.tliasservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tliasservice.mapper.EmpExprMapper;
import com.tliasservice.mapper.EmpMapper;
import com.tliasservice.pojo.*;
import com.tliasservice.service.EmpService;
import com.tliasservice.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> queryByPage(EmpQueryParam e) {
        PageHelper.startPage(e.getPage(),e.getPageSize());

        List<Emp> empList =empMapper.pageList(e);

        Page<Emp> p= (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), empList);
    }

    @Transactional
    @Override
    public void deleteById(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        Integer empId=emp.getId();

        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(empId);
            }
            empExprMapper.insert(exprList);
        }
    }

    @Override
    public Emp queryById(Integer id) {
        return empMapper.getInfo(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //删除旧的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //重新添加已经修改的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!exprList.isEmpty()){
            empExprMapper.insert(exprList);
        }


    }

    @Override
    public List<Emp> queryAllList() {
        return empMapper.list();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin=empMapper.getUserByUserNameAndPassword(emp);

        if (empLogin != null) {
            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("id",empLogin.getId());
            dataMap.put("username",empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            return new LoginInfo(empLogin.getId(),empLogin.getUsername(),empLogin.getName(),jwt);
        }

        return null;
    }
}
