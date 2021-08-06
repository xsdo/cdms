package com.qx.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 诊断记录对象 imp_record
 * 
 * @author aaa
 * @date 2021-06-18
 */
public class ImpRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 诊断记录ID */
    private Long id;

    /** 诊断ids */
    private String impIds;

    /** 拟诊类型（0主要，1次要，2鉴别） */
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImpIds() {
        return impIds;
    }

    public void setImpIds(String impIds) {
        this.impIds = impIds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("impIds", getImpIds())
            .append("type", getType())
            .toString();
    }
}
