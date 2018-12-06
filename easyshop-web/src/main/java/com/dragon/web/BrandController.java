package com.dragon.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dragon.MyFile;
import com.dragon.PageHelper;
import com.dragon.TimeUtils;
import com.dragon.bean.Brand;
import com.dragon.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dragon
 * @since 2018-12-05
 */
@Controller
@RequestMapping("/brand")
@Scope("prototype")
public class BrandController {
    @Reference
    BrandService bs;
    // 查询首页
    @RequestMapping("/list/{pageIndex}")
    public String list(Brand brand, @PathVariable("pageIndex") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "5")Integer pageSize, Model model) {
        Page<Brand> results = null;
        // 分页工具
        Page<Brand> page = new Page<Brand>(pageIndex, pageSize);
        EntityWrapper<Brand> entityWrapper = new EntityWrapper<Brand>();
        // 查询的数据结果集对象  查询条件2个
        if (brand != null && brand.getName() != null) {
            entityWrapper.like("name", brand.getName());
        }
        if (brand != null && brand.getChina() != null && brand.getChina() != -1) {
            entityWrapper.eq("china", brand.getChina());
        }
        results = bs.selectPage(page, entityWrapper.eq("del",0));
        // 获取总数
        int totalCount = ((Long) results.getTotal()).intValue();
        // 查询是否有上一页
        boolean hasPrevious = results.hasPrevious();
        // 查询是否有下一页
        boolean hasNext = results.hasNext();
        // 查询到每页数据
        List<Brand> brands = results.getRecords();
        // 查询出所有班级
        PageHelper<Brand> pageHelper = new PageHelper<Brand>(totalCount, pageIndex, pageSize, brands, brand);
        model.addAttribute("pageHelper", pageHelper);
        model.addAttribute("hasPrevious", hasPrevious);
        model.addAttribute("hasNext", hasNext);
        System.err.println(pageHelper);
        return "Brand_Manage";  //跳转模板上
    }
    //到更新界面去
    @RequestMapping("/pageToUpdate/{id}")
    public String pageToUpdate(@PathVariable Integer id,Model model){
        Brand brand = bs.selectById(id);
        model.addAttribute("brand",brand);
        return "Edit_Brand";
    }

    //改变状态
    @RequestMapping("/changeStatus/{id}")
    @ResponseBody
    public Boolean changeStatus(@PathVariable Integer id, Integer status){
        boolean id1 = bs.updateForSet("status="+status, new EntityWrapper<Brand>().eq("id", id));
        System.out.println("状态"+id1+",id"+id+"status."+status);
        return id1;
    }
    //删除
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id,HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf8");
        boolean id1 = bs.updateForSet("del="+1, new EntityWrapper<Brand>().eq("id", id));
        if(id1){
            response.getWriter().write("<script>alert('删除成功');location.href='/brand/list/1'</script>");
        }else{
            response.getWriter().write("<script>alert('删除失败');location.href='/brand/list/1'</script>");
        }
    }
    //批量删除
    @RequestMapping("/deletes")
    @ResponseBody
    public Boolean deletes(Integer[] ids){
        return bs.deletes(ids);
    }
    //改变状态
    @RequestMapping("/update")
    public void update(Brand brand, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf8");
        boolean b = bs.updateById(brand);
        System.out.println(b);
        if(b){
            response.getWriter().write("<script>alert('修改成功');location.href='/brand/list/1'</script>");
        }else{
            response.getWriter().write("<script>alert('修改失败');location.href='/brand/list/1'</script>");
        }
    }
    //改变状态
    @RequestMapping("/pageToAdd")
    public String pageToAdd() throws Exception{
        return "Add_Brand";
    }
    //添加
    @RequestMapping("/add")
    public void add(Brand brand, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置时间
        brand.setCreatetime(TimeUtils.getTime());
        //设置锁定状态为未锁定
        brand.setStatus(1);
        //设置删除状态为未删除
        brand.setDel(0);
        response.setContentType("text/html;charset=utf8");
        boolean insert = bs.insert(brand);
        if(insert){
            response.getWriter().write("<script>alert('添加成功');location.href='/brand/list/1'</script>");
        }else{
        response.getWriter().write("<script>alert('添加失败');location.href='/brand/list/1'</script>");
        }
        }
    @RequestMapping("/upload")
    @ResponseBody
    public MyFile uploadPic(MultipartFile file, HttpServletRequest request) throws Exception {
        MyFile myFile=new MyFile();
        try {
            // 开始上传图片
            // 1.加载大哥的路径.
            ClientGlobal.init(System.getProperty("user.dir")
                    + "\\easyshop-web-brand\\src\\main\\resources\\Tracker.conf");
            // 2.创建大哥的Client.
            TrackerClient tc = new TrackerClient();
            // 3.大哥的Client获得大哥的server
            TrackerServer ts = tc.getConnection();
            // 4.声明一个马仔的server.默认值给null
            StorageServer ss = null;
            // 5.通过大哥的server和马仔的server获得到马仔的客户端 StorageClient
            StorageClient sc = new StorageClient(ts, ss);
            // 6.使用马仔的Client上传图片.
            String[] result = sc.upload_appender_file(
                    file.getBytes(),
                    StringUtils.substringAfterLast(
                            file.getOriginalFilename(), "."), null);
            // 返回值result 封装有文件上传路径和文件名（随机生成的）
            // 当和添加工作一起做时，需要把文件的路径和文件名保存到数据库，因此需要该返回值（但不会包括图片服务器的ip地址和端口，别忘了添加进去）
            String imagePath = "http://192.168.159.139:8866/"+result[0]+"/"+result[1];
            System.out.println(imagePath);

            myFile.setMsg(imagePath);
            myFile.setCode("200");
            return myFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        myFile.setCode("505");
        return myFile;
    }
}

