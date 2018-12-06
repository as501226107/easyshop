package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.ContentCategory;
import com.dragon.mapper.ContentCategoryMapper;
import com.dragon.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {
    @Autowired
    ContentCategoryService cas;
    @Autowired
    ContentCategoryMapper ccm;
    @Override
    public Boolean deletes(Integer[] ids) {
        try {
            for (Integer id : ids) {
                ccm.updateForSet("del="+1,new EntityWrapper<ContentCategory>().eq("id",id));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
