package com.qx.cases.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qx.common.annotation.Excel;
import com.qx.common.core.domain.BaseEntity;

/**
 * 诊断依据对象 case_imp_basis
 * 
 * @author aaa
 * @date 2021-06-28
 */
public class CaseImpBasis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 依据ID */
    private Long id;

    /** 诊断ID */
    @Excel(name = "诊断ID")
    private Long impId;

    /** 诊断依据 */
    @Excel(name = "诊断依据")
    private String basis;

    /** 是否标题（0是 1否） */
    @Excel(name = "是否标题", readConverterExp = "0=是,1=否")
    private String flag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImpId(Long impId) 
    {
        this.impId = impId;
    }

    public Long getImpId() 
    {
        return impId;
    }
    public void setBasis(String basis) 
    {
        this.basis = basis;
    }

    public String getBasis() 
    {
        return basis;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("impId", getImpId())
            .append("basis", getBasis())
            .append("flag", getFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
