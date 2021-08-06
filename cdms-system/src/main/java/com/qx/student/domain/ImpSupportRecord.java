package com.qx.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 诊断依据支持记录对象 imp_support_record
 * 
 * @author aaa
 * @date 2021-06-28
 */
public class ImpSupportRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录id */
    private Long id;

    /** 诊断记录id */
    @Excel(name = "诊断记录id")
    private Long impRecordId;

    /** 诊断依据ID */
    @Excel(name = "诊断依据ID")
    private Long basisId;

    /** 支持状态（0支持 1不支持） */
    @Excel(name = "支持状态", readConverterExp = "0=支持,1=不支持")
    private String support;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImpRecordId(Long impRecordId) 
    {
        this.impRecordId = impRecordId;
    }

    public Long getImpRecordId() 
    {
        return impRecordId;
    }
    public void setBasisId(Long basisId) 
    {
        this.basisId = basisId;
    }

    public Long getBasisId() 
    {
        return basisId;
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
            .append("impRecordId", getImpRecordId())
            .append("basisId", getBasisId())
            .append("support", getSupport())
            .toString();
    }
}
