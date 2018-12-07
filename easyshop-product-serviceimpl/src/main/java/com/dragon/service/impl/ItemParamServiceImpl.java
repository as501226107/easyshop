package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.ItemParam;
import com.dragon.mapper.ItemParamMapper;
import com.dragon.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 商品规则参数 服务实现类
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {
    @Autowired
    ItemParamService ips;
}
