package com.qx.student.domain;

import com.qx.cases.domain.CaseCheckItem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 辅助检查项目支持记录对象 fzcheck_support_record
 * 
 * @author aaa
 * @date 2021-06-17
 */
public class FzcheckSupportRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 检查记录ID */
    @Excel(name = "检查记录ID")
    private Long checkRecordId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long itemId;

    /** 支持状态（0支持 1不支持） */
    @Excel(name = "支持状态", readConverterExp = "0=支持,1=不支持")
    private String support;

    /** 查询字段 **/
    private CaseCheckItem item;

    public CaseCheckItem getItem() {
        return item;
    }

    public void setItem(CaseCheckItem item) {
        this.item = item;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCheckRecordId(Long checkRecordId) 
    {
        this.checkRecordId = checkRecordId;
    }

    public Long getCheckRecordId() 
    {
        return checkRecordId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setSupport(String support) 
    {
        this.support = support;
    }

    public String getSupport() 
    {
        return support;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkRecordId", getCheckRecordId())
            .append("itemId", getItemId())
            .append("support", getSupport())
            .toString();
    }
}
