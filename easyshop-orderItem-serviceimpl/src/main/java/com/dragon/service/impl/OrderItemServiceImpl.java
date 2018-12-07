package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.dragon.bean.OrderItem;
import com.dragon.mapper.OrderItemMapper;
import com.dragon.service.OrderItemService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author little
 * @since 2018-12-06
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
