package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.Item;
import com.dragon.mapper.ItemMapper;
import com.dragon.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Autowired
    ItemService is;
}
