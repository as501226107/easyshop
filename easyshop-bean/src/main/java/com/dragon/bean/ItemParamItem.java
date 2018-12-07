package com.dragon.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品规格和商品的关系表
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@TableName("tb_item_param_item")
public class ItemParamItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID
     */
    private Long itemId;
    /**
     * 参数数据，格式为json格式
     */
    private String paramData;
    private Date created;
    private Date updated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
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
        return "ItemParamItem{" +
        ", id=" + id +
        ", itemId=" + itemId +
        ", paramData=" + paramData +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
