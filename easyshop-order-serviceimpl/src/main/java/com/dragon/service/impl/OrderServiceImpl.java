package com.dragon.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dragon.bean.Brand;
import com.dragon.bean.Order;
import com.dragon.mapper.BrandMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.mapper.OrderMapper;
import com.dragon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author little
 * @since 2018-12-06
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private OrderService orderService;

    public OrderServiceImpl() {
    }
    @Autowired
    public OrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }
}
