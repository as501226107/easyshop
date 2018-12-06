package com.dragon.web;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dragon.bean.Admin;
import com.dragon.service.AdminService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @RequestMapping("/pageTologin")
    public String index(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session, Model model){
     Admin result=   as.selectOne(new EntityWrapper<Admin>()
                .eq("loginname",admin.getLoginname())
                .eq("password",admin.getPassword()));
       if(result==null){
           model.addAttribute("msg","用户名或者密码错误");
       }
        else if(result!=null&&result.getDel()!=null&&result.getDel()==1){
            model.addAttribute("msg","用户不存在");
            return "login";
        }else if(result!=null&&result.getStatus()!=null&&result.getStatus()==0){
               model.addAttribute("msg","用户已经被锁定");
               return "login";
        }
        session.setAttribute("user",result);
        return "home";
    }
    @ResponseBody
    @RequestMapping("/list")
    public List<Admin> getList(){
       return as.selectList(null);
    }
}

