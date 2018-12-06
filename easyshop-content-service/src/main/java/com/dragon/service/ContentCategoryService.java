package com.dragon.service;

import com.dragon.bean.ContentCategory;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
public interface ContentCategoryService extends IService<ContentCategory> {
    public Boolean deletes(Integer[] ids);

}
