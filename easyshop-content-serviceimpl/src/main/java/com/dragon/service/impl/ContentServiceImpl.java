package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.Content;
import com.dragon.mapper.ContentMapper;
import com.dragon.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
@Service
@Transactional
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Autowired
    ContentService cs;
    @Autowired
    ContentMapper cm;
    @Override
    public Boolean deletes(Integer[] ids) {
        try {
            for (Integer id : ids) {
                cm.updateForSet("del="+1,new EntityWrapper<Content>().eq("id",id));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
