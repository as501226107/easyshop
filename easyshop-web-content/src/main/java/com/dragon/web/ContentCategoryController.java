package com.dragon.web;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dragon.PageHelper;
import com.dragon.TimeUtils;
import com.dragon.bean.ContentCategory;
import com.dragon.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 内容分类 前端控制器
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController {
    @Reference
    ContentCategoryService cas;

    @RequestMapping("/list")
    @ResponseBody
    public List<ContentCategory> list(){
        return cas.selectList(new EntityWrapper<ContentCategory>().eq("del",0));
    }

    // 查询首页
    @RequestMapping("/list/{pageIndex}")
    public String list(ContentCategory contentCategory, @PathVariable("pageIndex") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "5")Integer pageSize, Model model) {
        Page<ContentCategory> results = null;
        // 分页工具
        Page<ContentCategory> page = new Page<ContentCategory>(pageIndex, pageSize);
        EntityWrapper<ContentCategory> entityWrapper = new EntityWrapper<ContentCategory>();
        // 查询的数据结果集对象  查询条件2个
        if (contentCategory != null && contentCategory.getName() != null) {
            entityWrapper.like("name", contentCategory.getName());
        }
        results = cas.selectPage(page, entityWrapper.eq("del",0));
        // 获取总数
        int totalCount = ((Long) results.getTotal()).intValue();
        // 查询是否有上一页
        boolean hasPrevious = results.hasPrevious();
        // 查询是否有下一页
        boolean hasNext = results.hasNext();
        // 查询到每页数据
        List<ContentCategory> contentCategorys = results.getRecords();
        // 查询出所有班级
        PageHelper<ContentCategory> pageHelper = new PageHelper<ContentCategory>(totalCount, pageIndex, pageSize, contentCategorys, contentCategory);
        model.addAttribute("pageHelper", pageHelper);
        model.addAttribute("hasPrevious", hasPrevious);
        model.addAttribute("hasNext", hasNext);
        System.err.println(pageHelper);
        return "Sort_ads";  //跳转模板上
    }
    //改变状态
    @RequestMapping("/changeStatus/{id}")
    @ResponseBody
    public Boolean changeStatus(@PathVariable Integer id, Integer status){
        boolean id1 = cas.updateForSet("status="+status, new EntityWrapper<ContentCategory>().eq("id", id));
        System.out.println("状态"+id1+",id"+id+"status."+status);
        return id1;
    }
    //批量删除
    @RequestMapping("/deletes")
    @ResponseBody
    public Boolean deletes(Integer[] ids){
        return cas.deletes(ids);
    }
    //删除
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf8");
        boolean id1 = cas.updateForSet("del="+1, new EntityWrapper<ContentCategory>().eq("id", id));
        if(id1){
            response.getWriter().write("<script>alert('删除成功');location.href='/contentCategory/list/1'</script>");
        }else{
            response.getWriter().write("<script>alert('删除失败');location.href='/contentCategory/list/1'</script>");
        }
    }
    @ResponseBody
    @RequestMapping("/add")
    public Boolean add(ContentCategory contentCategory){
        contentCategory.setDel(0);
        contentCategory.setCreated(TimeUtils.getTime());
        return cas.insert(contentCategory);
    }
    @ResponseBody
    @RequestMapping("/get/{id}")
    public ContentCategory get(@PathVariable("id") Integer id){
        return cas.selectById(id);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Boolean update(ContentCategory contentCategory){
        return cas.updateById(contentCategory);
    }
}

