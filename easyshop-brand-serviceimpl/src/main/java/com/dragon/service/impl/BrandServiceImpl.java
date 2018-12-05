package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dragon.bean.Brand;
import com.dragon.mapper.BrandMapper;
import com.dragon.service.BrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-05
 */
@Service
@Transactional
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    BrandService bs;
    @Autowired
    BrandMapper mp;
    @Override
    public Boolean deletes(Integer[] ids) {
        try {
            for (Integer id : ids) {
                mp.updateForSet("del="+1,new EntityWrapper<Brand>().eq("id",id));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
