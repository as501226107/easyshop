package com.dragon.service;

import com.dragon.bean.Brand;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dragon
 * @since 2018-12-05
 */
public interface BrandService extends IService<Brand> {
    public Boolean deletes(Integer[] ids);
}
