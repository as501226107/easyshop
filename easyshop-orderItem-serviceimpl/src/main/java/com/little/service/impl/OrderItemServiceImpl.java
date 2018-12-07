package com.little.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dragon.bean.OrderItem;
import com.dragon.mapper.OrderItemMapper;
import com.little.service.OrderItemService;
import org.springframework.stereotype.Service;

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
