package com.dragon.web;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author little
 * @since 2018-12-06
 */
@Controller
@RequestMapping("/order")
@Scope("prototype")
public class OrderController {

}

