package com.dragon.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品规则参数
 * </p>
 *
 * @author dragon
 * @since 2018-12-07
 */
@TableName("tb_item_param")
public class ItemParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品类目ID
     */
    private Long itemCatId;
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

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
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
        return "ItemParam{" +
        ", id=" + id +
        ", itemCatId=" + itemCatId +
        ", paramData=" + paramData +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
