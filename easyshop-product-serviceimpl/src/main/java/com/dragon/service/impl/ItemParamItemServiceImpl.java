package com.dragon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.ItemParamItem;
import com.dragon.mapper.ItemParamItemMapper;
import com.dragon.service.ItemParamItemService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 商品规格和商品的关系表 服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@Service
public class ItemParamItemServiceImpl extends ServiceImpl<ItemParamItemMapper, ItemParamItem> implements ItemParamItemService {
    @Autowired
    ItemParamItemService ipis;
}
