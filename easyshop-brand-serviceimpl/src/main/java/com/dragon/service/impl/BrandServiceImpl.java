package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dragon.bean.Brand;
import com.dragon.mapper.BrandMapper;
import com.dragon.service.BrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-05
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    BrandService bs;
}
