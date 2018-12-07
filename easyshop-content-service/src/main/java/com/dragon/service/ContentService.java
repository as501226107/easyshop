package com.dragon.service;

import com.dragon.bean.Content;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
public interface ContentService extends IService<Content> {
    public Boolean deletes(Integer[] ids);
}
