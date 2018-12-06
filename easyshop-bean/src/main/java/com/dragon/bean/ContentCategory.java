package com.dragon.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 内容分类
 * </p>
 *
 * @author dragon
 * @since 2018-12-06
 */
@TableName("tb_content_category")
public class ContentCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;
    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sortOrder;
    /**
     * 该类目是否为父类目，1为true，0为false
     */
    private Integer isParent;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 创建时间
     */
    private String updated;
    //删除

    private int del;

    private String desc;


    //标记是否选中
    @TableField(exist = false)
    private boolean flag;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ContentCategory{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", status=" + status +
        ", sortOrder=" + sortOrder +
        ", isParent=" + isParent +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
