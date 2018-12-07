package com.dragon.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品描述表
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@TableName("tb_item_desc")
public class ItemDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "item_id", type = IdType.AUTO)
    private Long itemId;
    /**
     * 商品描述
     */
    private String itemDesc;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ItemDesc{" +
        ", itemId=" + itemId +
        ", itemDesc=" + itemDesc +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
