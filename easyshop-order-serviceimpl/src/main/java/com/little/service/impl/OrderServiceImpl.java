package com.little.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dragon.bean.Order;
import com.dragon.mapper.OrderMapper;
import com.little.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
