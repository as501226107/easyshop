package com.dragon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.ItemDesc;
import com.dragon.mapper.ItemDescMapper;
import com.dragon.service.ItemDescService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 商品描述表 服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@Service
public class ItemDescServiceImpl extends ServiceImpl<ItemDescMapper, ItemDesc> implements ItemDescService {
    @Autowired
    ItemDescService ids;
}
