package com.dragon.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.OrderShipping;
import com.dragon.mapper.OrderShippingMapper;
import com.dragon.service.OrderShippingService;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author little
 * @since 2018-12-06
 */
@Service
public class OrderShippingServiceImpl extends ServiceImpl<OrderShippingMapper, OrderShipping> implements OrderShippingService {

}
