package com.dragon.web;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dragon.MyFile;
import com.dragon.PageHelper;
import com.dragon.TimeUtils;
import com.dragon.bean.Content;
import com.dragon.bean.ContentCategory;
import com.dragon.service.ContentCategoryService;
import com.dragon.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Reference
    ContentService cs;
    @Reference
    ContentCategoryService cas;
    @RequestMapping("/list/{pageIndex}")
    public String list(Content content, @PathVariable("pageIndex") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "5")Integer pageSize, Model model,Integer typeid){
        Page<Content> results = null;
        // 分页工具
        Page<Content> page = new Page<Content>(pageIndex, pageSize);
        EntityWrapper<Content> entityWrapper = new EntityWrapper<Content>();
        // 查询的数据结果集对象  查询条件title
        if (content != null && content.getTitle() != null) {
            entityWrapper.like("title", content.getTitle());
        }
        //根据类别查询
        if(typeid!=null&&typeid!=-1){
            entityWrapper.like("category_id",typeid+"");
        }
        //查询未删除的
        results = cs.selectPage(page, entityWrapper.eq("del",0));
        // 获取总数
        int totalCount = ((Long) results.getTotal()).intValue();
        // 查询是否有上一页
        boolean hasPrevious = results.hasPrevious();
        // 查询是否有下一页
        boolean hasNext = results.hasNext();
        // 查询到每页数据
        List<Content> brands = results.getRecords();
        //封装广告图分类
        List<Map<String,Object>> types=new ArrayList<>();

        //查询出所有的分类
        List<ContentCategory> alls = cas.selectList(new EntityWrapper<ContentCategory>().eq("del", 0));
        //查出所有的条数
        int nums = (int) cs.selectCount(new EntityWrapper<Content>().eq("del",0));
        for (ContentCategory all : alls) {
            int counts = cs.selectCount(new EntityWrapper<Content>().eq("category_id", all.getId()));
            HashMap<String,Object> map=new HashMap<>();//数据结构： [{类型(type),数量(num)}]
            map.put("type",all);
            map.put("nums",counts);
            System.out.println(map);
            types.add(map);
        }
        // 查询出所有班级
        PageHelper<Content> pageHelper = new PageHelper<Content>(totalCount, pageIndex, pageSize, brands, content);
        model.addAttribute("pageHelper", pageHelper);
        model.addAttribute("hasPrevious", hasPrevious);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("types", types);
        model.addAttribute("nums", nums);//数据总条数
        System.err.println(pageHelper);
        return "advertising";
    }
    //改变状态
    @RequestMapping("/changeStatus/{id}")
    @ResponseBody
    public Boolean changeStatus(@PathVariable Integer id, Integer status){
        boolean id1 = cs.updateForSet("status="+status, new EntityWrapper<Content>().eq("id", id));
        System.out.println("状态"+id1+",id"+id+"status."+status);
        return id1;
    }
    //批量删除
    @RequestMapping("/deletes")
    @ResponseBody
    public Boolean deletes(Integer[] ids){
        return cs.deletes(ids);
    }
    //删除
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf8");
        boolean id1 = cs.updateForSet("del="+1, new EntityWrapper<Content>().eq("id", id));
        if(id1){
            response.getWriter().write("<script>alert('删除成功');location.href='/content/list/1'</script>");
        }else{
            response.getWriter().write("<script>alert('删除失败');location.href='/content/list/1'</script>");
        }
    }
    //添加
    @RequestMapping("/add")
    @ResponseBody
    public Boolean add(Content content, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置时间
        //设置锁定状态为未锁定
        content.setStatus(1);
        //设置删除状态为未删除
        content.setDel(0);
        //设置创建时间
        content.setCreated(TimeUtils.getTime());
        //设置更新时间
        content.setUpdated(TimeUtils.getTime());
        response.setContentType("text/html;charset=utf8");
        boolean insert = cs.insert(content);
        if(insert){
            return true;
        }else{
            return  false;
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
                    + "\\easyshop-web-content\\src\\main\\resources\\Tracker.conf");
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
    @RequestMapping("/toupdate/{id}")
    @ResponseBody
    public Map<String,Object> listWithFlag(@PathVariable("id") Integer id){
        HashMap<String,Object> map=new HashMap<>();
        Content content = cs.selectById(id);
        List<ContentCategory> del = cas.selectList(new EntityWrapper<ContentCategory>().eq("del", 0));
        for (ContentCategory contentCategory : del) {
            if(content.getCategoryId()==contentCategory.getId()){
                contentCategory.setFlag(true);
            }
        }
        map.put("content",content);
        map.put("list",del);
        return map;
    }
    @ResponseBody
    @RequestMapping("/update/{id}")
    public Boolean update(Content content,@PathVariable("id") Long id){
        content.setId(id);
        return cs.updateById(content);
    }
}

