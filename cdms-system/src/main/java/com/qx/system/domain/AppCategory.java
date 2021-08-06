package com.qx.system.domain;

import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类对象 app_category
 * 
 * @author fx
 * @date 2019-12-18
 */
public class AppCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类目id */
    private Long id;

    /** 类目名称 */
    @Excel(name = "类目名称")
    private String name;

    /** 类目关键字，以JSON数组格式 */
    @Excel(name = "类目关键字，以JSON数组格式")
    private String keywords;

    /** 分类介绍 */
    @Excel(name = "分类介绍")
    private String frontDesc;

    /** 父类目ID */
    @Excel(name = "父类目ID")
    private Long parentId;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;

    /** 分类图标 */
    @Excel(name = "分类图标")
    private String iconUrl;

    /** 分类图片 */
    @Excel(name = "分类图片")
    private String picUrl;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    
    /** 父部门名称 */
    private String parentName;
    
    /** 子分类 */
    private List<AppCategory> children = new ArrayList<AppCategory>();

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setFrontDesc(String frontDesc) 
    {
        this.frontDesc = frontDesc;
    }

    public String getFrontDesc() 
    {
        return frontDesc;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setIconUrl(String iconUrl) 
    {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() 
    {
        return iconUrl;
    }
    public void setPicUrl(String picUrl) 
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl() 
    {
        return picUrl;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    
    public List<AppCategory> getChildren() {
		return children;
	}

	public void setChildren(List<AppCategory> children) {
		this.children = children;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("keywords", getKeywords())
            .append("frontDesc", getFrontDesc())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("iconUrl", getIconUrl())
            .append("picUrl", getPicUrl())
            .append("orderNum", getOrderNum())
            .append("code", getCode())
            .append("level", getLevel())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
