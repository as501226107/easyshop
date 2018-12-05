package com.dragon.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dragon.bean.Admin;
import com.dragon.service.AdminService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dragon
 * @since 2018-12-04
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminController {
    @Reference
    AdminService as;
    @ResponseBody
    @RequestMapping("/list")
    public List<Admin> getList(){
       return as.selectList(null);
    }
}

